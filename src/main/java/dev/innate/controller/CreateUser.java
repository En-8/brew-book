package dev.innate.controller;

import dev.innate.entity.Role;
import dev.innate.entity.User;
import dev.innate.persistance.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet that handles requests to create a new user.
 */
@WebServlet(urlPatterns = {"/createUser"})
public class CreateUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("userId") != null) {
            // Getting here will only be possible via direct navigation.
            response.sendRedirect("manageAccount");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("createUser.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User newUser = new User();
        newUser.setUsername(request.getParameter("username"));
        newUser.setEmail(request.getParameter("email"));
        newUser.setPassword(request.getParameter("password"));

        if (checkIfUsernameExists(newUser.getUsername())) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("createUser.jsp");
            request.setAttribute("error", "A user with that username already exists");
            dispatcher.forward(request, response);
        } else {
            GenericDao dao = new GenericDao(User.class);
            int userId = dao.create(newUser);
            newUser.setId(userId);

            GenericDao roleDao = new GenericDao(Role.class);
            Role role = new Role();
            role.setUser(newUser);
            role.setUsername(newUser.getUsername());
            role.setRoleName("registered_user");
            roleDao.create(role);

            HttpSession session = request.getSession();
            session.setAttribute("username", newUser.getUsername());
            session.setAttribute("userId", userId);
            session.setAttribute("email", newUser.getEmail());

            response.sendRedirect("login");
        }
    }

    private boolean checkIfUsernameExists(String username) {
        GenericDao userDao = new GenericDao(User.class);
        return userDao.findByPropertyEqual("username", username).size() > 0;
    }
}
