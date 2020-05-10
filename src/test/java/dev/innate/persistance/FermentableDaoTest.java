package dev.innate.persistance;

import dev.innate.entity.Fermentable;
import dev.innate.test.util.Database;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.PersistenceException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FermentableDaoTest {
    private GenericDao fermentableDao;

    @BeforeEach
    public void setup() {
        fermentableDao = new GenericDao(Fermentable.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getAllFermentablesSuccess() {
        List<Fermentable> fermentables = fermentableDao.getAll();
        assertEquals(1, fermentables.size());
    }

    @Test
    void getFermentableById() {
        Fermentable fermentable = (Fermentable) fermentableDao.getById(1);
        assertEquals("American Wheat", fermentable.getName());
    }

    @Test
    void updateFermentable() {
        String newFermentableName = "6-row barley";
        Fermentable fermentableToUpdate = (Fermentable) fermentableDao.getById(1);
        fermentableToUpdate.setName(newFermentableName);
        fermentableDao.update(fermentableToUpdate);
        Fermentable retrievedFermentable = (Fermentable) fermentableDao.getById(1);
        assertEquals(newFermentableName, retrievedFermentable.getName());
    }

    @Test
    void fkConstraintBlocksFermentableDelete() {
        Fermentable fermentable = (Fermentable) fermentableDao.getById(1);
        Assert.assertThrows(PersistenceException.class, () -> fermentableDao.delete(fermentable));
    }

    @Test
    void createFermentable() {
        Fermentable newFermentable = new Fermentable();
        newFermentable.setId(10);
        newFermentable.setName("Flaked corn");
        int id = fermentableDao.create(newFermentable);
        Fermentable newFermentableRetrieved = (Fermentable) fermentableDao.getById(id);
        assertEquals(newFermentable, newFermentableRetrieved);
    }
}
