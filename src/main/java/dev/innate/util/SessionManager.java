package dev.innate.util;

import dev.innate.entity.User;
import dev.innate.persistance.GenericDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

/**
 * This is a singleton class that manages how user data is stored in the Http session.
 */
public class SessionManager {
    private static Logger logger = LogManager.getLogger(SessionManager.class);

    /**
     * Adds the specified user to the Http session. If that user is already in the session, or if there is no
     * user currently logged in, nothing is added to the session.
     *
     * @param username the username of the user being added to the session
     * @param session the Https session the user is being added to.
     */
    public static void addUserToSession(String username, HttpSession session) {
        String sessionUsername = (String) session.getAttribute("username");
        if (sessionUsername != null) {
            logger.log(Level.INFO, "User " + username + " already in session");
        } else if(username == null) {
            logger.log(Level.INFO, session.getId() + " No user to add to session; Proceeding as unregistered");
        } else {
            User currentUser = fetchUserData(username);
            session.setAttribute("username", currentUser.getUsername());
            session.setAttribute("userId", currentUser.getId());
            session.setAttribute("email", currentUser.getEmail());
            logger.log(Level.INFO, session.getId() + " User " + currentUser.getUsername() + " added to session");
        }
    }

    /**
     * Fetches the user data for the user that is being added to the session.
     *
     * @param username
     * @return an instance of User representing the current user.
     */
    private static User fetchUserData(String username) {
        GenericDao userDao = new GenericDao(User.class);
        return (User) userDao.findByPropertyEqual("username", username).get(0);
    }
}
