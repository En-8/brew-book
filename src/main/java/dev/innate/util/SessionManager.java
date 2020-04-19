package dev.innate.util;

import dev.innate.entity.User;
import dev.innate.persistance.GenericDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

public class SessionManager {
    private static Logger logger = LogManager.getLogger(SessionManager.class);

    public static void addUserToSession(String username, HttpSession session) {
        String sessionUsername = (String) session.getAttribute("username");
        if (sessionUsername != null) {
            logger.log(Level.INFO, "User " + username + " already in session");
        } else if(username == null) {
            logger.log(Level.INFO, "No user to add to session; Proceeding as unregistered");
        } else {
            User currentUser = fetchUserData(username);
            session.setAttribute("username", currentUser.getUsername());
            session.setAttribute("userId", currentUser.getId());
            session.setAttribute("email", currentUser.getEmail());
            logger.log(Level.INFO, "User " + currentUser.getUsername() + " added to session");
        }
    }

    private static User fetchUserData(String username) {
        GenericDao userDao = new GenericDao(User.class);
        return (User) userDao.findByPropertyEqual("username", username).get(0);
    }
}
