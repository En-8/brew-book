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

public class BrewDaoTest {
    GenericDao brewDao;
    User user;
    Yeast yeast;
    Style style;

    @BeforeEach
    void setUp() {
        brewDao = new GenericDao(Brew.class);
        user = new User("test", "test", "test@test.com");
        user.setId(1);
        yeast = new Yeast("Wyeast", "American Wheat");
        yeast.setId(1);
        style = new Style("Farmhouse Ale");
        style.setId(1);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getAllBrewsSuccess() {
        List<Brew> brews = brewDao.getAll();
        assertEquals(1, brews.size());
    }

    @Test
    void getBrewByIdSuccess() {
        Brew brew = (Brew) brewDao.getById(1);
        assertEquals("Spotted Cow", brew.getBrewName());
    }

    @Test
    void getBrewsByUserSuccess() {
        List<Brew> brews = brewDao.findByPropertyEqual("user", user);
        assertEquals(1, brews.size());
    }

    @Test
    void brewDeleteHasNoAffectOnUser() {
        GenericDao userDao = new GenericDao(User.class);
        Brew brew = (Brew) brewDao.getById(1);
        brewDao.delete(brew);
        User user = (User) userDao.getById(1);

        assertNotNull(user);
        assertEquals(this.user, user); // TODO feedback on this -- it seems fragile.
    }

    @Test
    void updateBrewSuccess() {
        String newDescription = "This is an updated description";
        Brew brewToUpdate = (Brew) brewDao.getById(1);
        brewToUpdate.setDescription(newDescription);
        brewDao.update(brewToUpdate);
        Brew retrievedBrew = (Brew) brewDao.getById(1);
        assertEquals(newDescription, retrievedBrew.getDescription());
    }

    @Test
    void deleteBrewSuccess() {
        Brew brew = (Brew) brewDao.getById(1);
        brewDao.delete(brew);
        assertNull(brewDao.getById(1));
    }

    @Test
    void createBrewSuccess() {
        Brew newBrew = new Brew(
                "Chocolate milk stout",
                "Silky smooth stout with cocoa nibs and lactose",
                "City of Madison faucet water",
                "Yeast pitched at 75 degrees",
                yeast,
                style,
                user
        );

        int id = brewDao.create(newBrew);
        Brew newBrewRetrieved = (Brew) brewDao.getById(id);
        assertEquals(newBrew, newBrewRetrieved);
    }
}
