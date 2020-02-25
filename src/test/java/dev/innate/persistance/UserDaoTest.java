package dev.innate.persistance;

import dev.innate.entity.Brew;
import dev.innate.entity.User;
import dev.innate.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.NoResultException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    UserDao dao;


    @BeforeEach
    void setUp() {
        dao = new UserDao();
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getAllUsersSuccess() {
        List<User> users = dao.getAllUsers();
        assertEquals(2, users.size());
    }

    @Test
    void getUserById() {
        User user = dao.getUserById(1);
        assertEquals("test", user.getUsername());
    }

    @Test
    void updateUser() {
        String newUsername = "new";
        User userToUpdate = dao.getUserById(1);
        userToUpdate.setUsername(newUsername);
        dao.updateUser(userToUpdate);
        User retrievedUser = dao.getUserById(1);
        assertEquals(newUsername, retrievedUser.getUsername());
    }

    @Test
    void deleteUser() {
        User user = dao.getUserById(1);
        dao.deleteUser(user);
        assertThrows(NoResultException.class, () -> {dao.getUserById(1);});
    }

    @Test
    void createUser() {
        User newUser = new User("testing", "testing", "testing@test.com");
        int id = dao.createUser(newUser);
        User newUserRetrieved = dao.getUserById(id);
        assertEquals(newUser, newUserRetrieved);
    }

    @Test
    void createUserWithBrewSuccess() {
        User newUser = new User("testing", "testing", "testing@test.com");

        Brew brew = new Brew("TestBrew", "description", "water", "pitch", 1, 1, newUser);
        newUser.addBrew(brew);

        int id = dao.createUser(newUser);
        User newUserRetrieved = dao.getUserById(id);
        assertEquals(newUser, newUserRetrieved);
    }
}