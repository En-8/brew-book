package dev.innate.persistance;

import dev.innate.entity.Misc;
import dev.innate.test.util.Database;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.PersistenceException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MiscDaoTest {
    private GenericDao miscDao;

    @BeforeEach
    public void setup() {
        miscDao = new GenericDao(Misc.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getAllMiscsSuccess() {
        List<Misc> miscs = miscDao.getAll();
        assertEquals(1, miscs.size());
    }

    @Test
    void getMiscById() {
        Misc misc = (Misc) miscDao.getById(1);
        assertEquals("Cranberries", misc.getName());
    }

    @Test
    void updateMisc() {
        String newMiscName = "Blackberries";
        Misc miscToUpdate = (Misc) miscDao.getById(1);
        miscToUpdate.setName(newMiscName);
        miscDao.update(miscToUpdate);
        Misc retrievedMisc = (Misc) miscDao.getById(1);
        assertEquals(newMiscName, retrievedMisc.getName());
    }

    @Test
    void deleteMisc() {
        Misc misc = (Misc) miscDao.getById(1);
        Assert.assertThrows(PersistenceException.class, () -> miscDao.delete(misc));
    }

    @Test
    void createMisc() {
        Misc newMisc = new Misc();
        newMisc.setId(10);
        newMisc.setName("Blueberries");
        int id = miscDao.create(newMisc);
        Misc newMiscRetrieved = (Misc) miscDao.getById(id);
        assertEquals(newMisc, newMiscRetrieved);
    }
}
