package dev.innate.controller;

import dev.innate.entity.Role;
import dev.innate.entity.User;
import dev.innate.persistance.GenericDao;
import dev.innate.util.SessionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Servlet that handles requests for account management
 */
@WebServlet(urlPatterns = {"/manageAccount"})
public class UserAccountManagement extends HttpServlet {
    Logger logger = LogManager.getLogger();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        SessionManager.addUserToSession(request.getRemoteUser(), session);

        RequestDispatcher dispatcher = request.getRequestDispatcher("accountManagement.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");
        String currentUsername = (String) session.getAttribute("username");
        String newUsername = request.getParameter("username");
        String newEmail = request.getParameter("email");

        GenericDao userDao = new GenericDao(User.class);

        logger.info("User before update: " + userId + ", " + currentUsername);

        User user = (User) userDao.getById(userId);
        user.setUsername(newUsername);
        user.setEmail(newEmail);
        logger.info("User with updated info (in memory): " + user);

        userDao.update(user);

        // Update the roles associated with this user
        GenericDao roleDao = new GenericDao(Role.class);
        List<Role> currentRoles = roleDao.findByPropertyEqual("username", currentUsername);
        currentRoles.forEach(role -> {
            role.setUsername(newUsername);
            roleDao.update(role);
        });

        // Update the session with the new information
        session.setAttribute("email", newEmail);
        session.setAttribute("username", newUsername);

        response.sendRedirect("manageAccount");
    }
}
