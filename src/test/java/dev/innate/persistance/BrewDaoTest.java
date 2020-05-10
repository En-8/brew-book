package dev.innate.persistance;

import dev.innate.entity.*;
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
    BrewFermentable bf;
    BrewHop bh;
    BrewMisc bm;
    Fermentable fermentable;
    Hop hop;
    Misc misc;

    @BeforeEach
    void setUp() {
        brewDao = new GenericDao(Brew.class);
        user = new User("test", "test", "test@test.com");
        user.setId(1);
        yeast = new Yeast("Wyeast", "Belgian yeast");
        yeast.setId(1);
        style = new Style("Farmhouse Ale");
        style.setId(1);
        bf = new BrewFermentable(10, "lb");
        bf.setId(1);
        bh = new BrewHop(21, "boil", 12, "min", "oz");
        bh.setId(1);
        bm = new BrewMisc(12, "lb", "fermentation", 24, "hr");
        bm.setId(1);
        fermentable = new Fermentable("American Wheat");
        fermentable.setId(1);
        bf.setFermentable(fermentable);
        hop = new Hop();
        hop.setName("Citra");
        hop.setId(1);
        bh.setHop(hop);
        misc = new Misc();
        misc.setId(1);
        misc.setName("Cranberries");
        bm.setMisc(misc);

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
    void getBrewByIdNotFound() {
        Brew brew = (Brew) brewDao.getById(99999);
        assertNull(brew);
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
        assertEquals(this.user, user);
    }

    @Test
    public void brewDeleteCascadesToBrewFermentable() {
        GenericDao bfDao = new GenericDao(BrewFermentable.class);
        Brew brew = (Brew) brewDao.getById(1);
        brewDao.delete(brew);
        BrewFermentable bf = (BrewFermentable) bfDao.getById(1);

        assertNull(bf);
    }

    @Test
    public void brewDeleteCascadesToBrewHop() {
        GenericDao bhDao = new GenericDao(BrewHop.class);
        Brew brew = (Brew) brewDao.getById(1);
        brewDao.delete(brew);
        BrewHop bh = (BrewHop) bhDao.getById(1);

        assertNull(bh);
    }

    @Test
    public void brewDeleteCascadesToBrewMisc() {
        GenericDao bmDao = new GenericDao(BrewMisc.class);
        Brew brew = (Brew) brewDao.getById(1);
        brewDao.delete(brew);
        BrewMisc bm = (BrewMisc) bmDao.getById(1);

        assertNull(bm);
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
        newBrew.addBrewFermentable(bf);
        newBrew.addBrewMisc(bm);
        newBrew.addBrewHop(bh);

        int id = brewDao.create(newBrew);
        Brew newBrewRetrieved = (Brew) brewDao.getById(id);
        assertEquals(newBrew, newBrewRetrieved);
    }
}
