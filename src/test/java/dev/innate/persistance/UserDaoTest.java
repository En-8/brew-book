package dev.innate.persistance;

import dev.innate.entity.Brew;
import dev.innate.entity.Style;
import dev.innate.entity.User;
import dev.innate.entity.Yeast;
import dev.innate.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    GenericDao userDao;
    Yeast yeast;
    Style style;

    @BeforeEach
    void setUp() {
        yeast = new Yeast("Wyeast", "American Wheat");
        yeast.setId(1);
        style = new Style("Farmhouse Ale");
        style.setId(1);

        userDao = new GenericDao(User.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getAllUsersSuccess() {
        List<User> users = userDao.getAll();
        assertEquals(2, users.size());
    }

    @Test
    void getUserById() {
        User user = (User) userDao.getById(1);
        assertEquals("test", user.getUsername());
    }

    @Test
    void updateUser() {
        String newUsername = "new";
        User userToUpdate = (User) userDao.getById(1);
        userToUpdate.setUsername(newUsername);
        userDao.update(userToUpdate);
        User retrievedUser = (User) userDao.getById(1);
        assertEquals(newUsername, retrievedUser.getUsername());
    }

    @Test
    void deleteUser() {
        User user = (User) userDao.getById(1);
        userDao.delete(user);
        assertNull(userDao.getById(1));
    }

    @Test
    void onUserDeleteBrewsDelete() {
        GenericDao brewDao = new GenericDao(Brew.class);
        User user = (User) userDao.getById(1);
        userDao.delete(user);
        assertEquals(0, brewDao.findByPropertyEqual("user", user).size());
    }

    @Test
    void createUser() {
        User newUser = new User("testing", "testing", "testing@test.com");
        int id = userDao.create(newUser);
        User newUserRetrieved = (User) userDao.getById(id);
        assertEquals(newUser, newUserRetrieved);
    }

    @Test
    void createUserWithBrewSuccess() {
        User newUser = new User("testing", "testing", "testing@test.com");

        Brew brew = new Brew("TestBrew", "description", "water", "pitch", yeast, style, newUser);
        newUser.addBrew(brew);

        int id = userDao.create(newUser);
        User newUserRetrieved = (User) userDao.getById(id);
        assertEquals(newUser, newUserRetrieved);
    }
}