package dev.innate.persistance;

import dev.innate.entity.Brew;
import dev.innate.entity.User;
import dev.innate.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.NoResultException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BrewDaoTest {
    BrewDao dao;
    User user;


    @BeforeEach
    void setUp() {
        dao = new BrewDao();
        user = new User("test", "test", "tester@test.com");
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getAllBrewsSuccess() {
        List<Brew> brews = dao.getAllBrews();
        assertEquals(2, brews.size());
    }

    @Test
    void getBrewByIdSuccess() {
        Brew brew = dao.getBrewById(1);
        assertEquals("test", brew.getBrewName());
    }

    @Test
    void getBrewsByUserSuccess() {
        // TODO
        assertEquals(0, 1);
    }

    @Test
    void updateBrewSuccess() {
        String newDescription = "This is an updated description";
        Brew brewToUpdate = dao.getBrewById(1);
        brewToUpdate.setDescription(newDescription);
        dao.updateBrew(brewToUpdate);
        Brew retrievedBrew = dao.getBrewById(1);
        assertEquals(newDescription, retrievedBrew.getDescription());
    }

    @Test
    void deleteBrewSuccess() {
        Brew brew = dao.getBrewById(1);
        dao.deleteBrew(brew);
        assertThrows(NoResultException.class, () -> {dao.getBrewById(1);});
    }

    @Test
    void createBrewSuccess() {
        Brew newBrew = new Brew(
                "Spotted Cow",
                "Refereshingly simple farmhouse ale",
                "City of Madison faucet water",
                "Yeast pitched at 75 degrees",
                5,
                12,
                user
        );

        int id = dao.createBrew(newBrew);
        Brew newBrewRetrieved = dao.getBrewById(id);
        assertEquals(newBrew, newBrewRetrieved);
    }
}
