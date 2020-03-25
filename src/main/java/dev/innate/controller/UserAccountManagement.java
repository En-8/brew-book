package dev.innate.controller;

import dev.innate.entity.Brew;
import dev.innate.entity.User;
import dev.innate.persistance.GenericDao;
import org.apache.logging.log4j.Level;
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

@WebServlet(urlPatterns = {"/manageAccount"})
public class UserAccountManagement extends HttpServlet {
    Logger logger = LogManager.getLogger();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        logger.log(Level.INFO, "Retrieved user " + user.getUsername() + " from HTTP session.");

        request.setAttribute("user", fetchUserData(user.getUsername()));
        RequestDispatcher dispatcher = request.getRequestDispatcher("accountManagement.jsp");
        dispatcher.forward(request, response);
    }

    private User fetchUserData(String username) {
        GenericDao userDao = new GenericDao(User.class);
        return (User) userDao.findByPropertyEqual("username", username).get(0);
    }
}
