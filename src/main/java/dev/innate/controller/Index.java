package dev.innate.controller;

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

@WebServlet(urlPatterns = {"/", "/index"})
public class Index extends HttpServlet {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");

        String username = request.getRemoteUser();
        if (username != null && !username.isBlank()) {
            User currentUser = addUserToSession(username, request.getSession());
            logger.log(Level.INFO, "User " + currentUser.getUsername() + " is currently logged in.");
        }

        dispatcher.forward(request, response);
    }

    private User addUserToSession(String username, HttpSession session) {
        User currentUser = null;

        if (session.getAttribute("user") != null) {
            currentUser = (User) session.getAttribute("user");
            logger.log(Level.INFO, "User " + currentUser.getUsername() + " already in session");
        } else {
            currentUser = fetchUserData(username);
            session.setAttribute("user", currentUser);
            logger.log(Level.INFO, "User " + currentUser.getUsername() + " added to session");
        }

        return currentUser;
    }

    private User fetchUserData(String username) {
        GenericDao userDao = new GenericDao(User.class);
        return (User) userDao.findByPropertyEqual("username", username).get(0);
    }
}
