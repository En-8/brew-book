package dev.innate.persistance;

import dev.innate.entity.Brew;
import dev.innate.entity.Style;
import dev.innate.entity.User;
import dev.innate.entity.Yeast;
import dev.innate.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.NoResultException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    GenericDao dao;
    Yeast yeast;
    Style style;

    @BeforeEach
    void setUp() {
        yeast = new Yeast("Wyeast", "American Wheat");
        yeast.setId(1);
        style = new Style("Farmhouse Ale");
        style.setId(1);

        dao = new GenericDao(User.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getAllUsersSuccess() {
        List<User> users = dao.getAll();
        assertEquals(2, users.size());
    }

    @Test
    void getUserById() {
        User user = (User)dao.getById(1);
        assertEquals("test", user.getUsername());
    }

    @Test
    void updateUser() {
        String newUsername = "new";
        User userToUpdate = (User)dao.getById(1);
        userToUpdate.setUsername(newUsername);
        dao.update(userToUpdate);
        User retrievedUser = (User)dao.getById(1);
        assertEquals(newUsername, retrievedUser.getUsername());
    }

    @Test
    void deleteUser() {
        User user = (User)dao.getById(1);
        dao.delete(user);
        assertNull(dao.getById(1));
    }

    @Test
    void createUser() {
        User newUser = new User("testing", "testing", "testing@test.com");
        int id = dao.create(newUser);
        User newUserRetrieved = (User)dao.getById(id);
        assertEquals(newUser, newUserRetrieved);
    }

    @Test
    void createUserWithBrewSuccess() {
        User newUser = new User("testing", "testing", "testing@test.com");

        Brew brew = new Brew("TestBrew", "description", "water", "pitch", yeast, style, newUser);
        newUser.addBrew(brew);

        int id = dao.create(newUser);
        User newUserRetrieved = (User)dao.getById(id);
        assertEquals(newUser, newUserRetrieved);
    }
}