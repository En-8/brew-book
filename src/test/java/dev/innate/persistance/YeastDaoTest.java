package dev.innate.persistance;

import dev.innate.entity.Yeast;
import dev.innate.test.util.Database;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.PersistenceException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YeastDaoTest {
    private GenericDao yeastDao;

    @BeforeEach
    public void setup() {
        yeastDao = new GenericDao(Yeast.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getAllYeastsSuccess() {
        List<Yeast> yeasts = yeastDao.getAll();
        assertEquals(1, yeasts.size());
    }

    @Test
    void getYeastById() {
        Yeast yeast = (Yeast) yeastDao.getById(1);
        assertEquals("Belgian yeast", yeast.getName());
    }

    @Test
    void updateYeast() {
        String newYeastName = "American Wheat";
        Yeast yeastToUpdate = (Yeast) yeastDao.getById(1);
        yeastToUpdate.setName(newYeastName);
        yeastDao.update(yeastToUpdate);
        Yeast retrievedYeast = (Yeast) yeastDao.getById(1);
        assertEquals(newYeastName, retrievedYeast.getName());
    }

    @Test
    void fkConstraintBlocksDeleteYeast() {
        Yeast yeast = (Yeast) yeastDao.getById(1);
        Assert.assertThrows(PersistenceException.class, () -> yeastDao.delete(yeast));
    }

    @Test
    void createYeast() {
        Yeast newYeast = new Yeast();
        newYeast.setId(10);
        newYeast.setBrand("Wyeast");
        newYeast.setName("American Wheat");
        int id = yeastDao.create(newYeast);
        Yeast newYeastRetrieved = (Yeast) yeastDao.getById(id);
        assertEquals(newYeast, newYeastRetrieved);
    }
}
