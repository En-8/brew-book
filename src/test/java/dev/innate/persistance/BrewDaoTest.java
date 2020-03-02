package dev.innate.persistance;

import dev.innate.entity.Brew;
import dev.innate.entity.User;
import dev.innate.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BrewDaoTest {
    GenericDao dao;
    User user;


    @BeforeEach
    void setUp() {
        dao = new GenericDao(Brew.class);
        user = new User("test", "test", "tester@test.com");
        user.setId(1);
        Database database = Database.getInstance();
        database.runSQL("cleandb_brew.sql");
    }

    @Test
    void getAllBrewsSuccess() {
        List<Brew> brews = dao.getAll();
        assertEquals(1, brews.size());
    }

    @Test
    void getBrewByIdSuccess() {
        Brew brew = (Brew)dao.getById(1);
        assertEquals("Spotted Cow", brew.getBrewName());
    }

    // TODO figure out how to implement this in the dao
//    @Test
//    void getBrewsByUserSuccess() {
//        List<Brew> brews = dao.getBrewsByUser(user.getId());
//        assertEquals(1, brews.size());
//    }

    @Test
    void updateBrewSuccess() {
        String newDescription = "This is an updated description";
        Brew brewToUpdate = (Brew)dao.getById(1);
        brewToUpdate.setDescription(newDescription);
        dao.update(brewToUpdate);
        Brew retrievedBrew = (Brew)dao.getById(1);
        assertEquals(newDescription, retrievedBrew.getDescription());
    }

    @Test
    void deleteBrewSuccess() {
        Brew brew = (Brew)dao.getById(1);
        dao.delete(brew);
        assertNull(dao.getById(1));
    }

    @Test
    void createBrewSuccess() {
        Brew newBrew = new Brew(
                "Chocolate milk stout",
                "Silky smooth stout with cocoa nibs and lactose",
                "City of Madison faucet water",
                "Yeast pitched at 75 degrees",
                5,
                12,
                user
        );

        int id = dao.create(newBrew);
        Brew newBrewRetrieved = (Brew)dao.getById(id);
        assertEquals(newBrew, newBrewRetrieved);
    }
}
