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
import java.util.List;

@WebServlet(urlPatterns = {"/allBrews", "/brewsByUser", "/yourBrews"})
public class BrowseBrews extends HttpServlet {
    Logger logger = LogManager.getLogger();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao brewDao = new GenericDao(Brew.class);
        String jspURL = "";
        List<Brew> brews = null;

        String uri = request.getRequestURI();
        if (uri.substring(uri.lastIndexOf("/")).equals("/yourBrews")) {
            String username = request.getRemoteUser();
            User currentUser = addUserToSession(username, request.getSession());

            jspURL = "yourBrews.jsp";
            brews = brewDao.findByPropertyEqual("user", currentUser);
        } else if (uri.substring(uri.lastIndexOf("/")).equals("/brewsByUser")) {
            String username = request.getParameter("username");
            User user = fetchUserData(username);

            jspURL = "brewsByUser.jsp";
            brews = brewDao.findByPropertyEqual("user", user);
        } else {
            jspURL = "browseAllBrews.jsp";
            brews = brewDao.getAll();
        }

        request.setAttribute("brews", brews);
        RequestDispatcher dispatcher = request.getRequestDispatcher(jspURL);
        dispatcher.forward(request, response);
    }

    /**
     * Adds the currently logged in user to the session.
     * If there is already a user in the session, this method
     * will return that user, instead.
     *
     * @param username
     * @param session
     * @return the current User object
     */
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

