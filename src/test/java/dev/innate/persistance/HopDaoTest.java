package dev.innate.persistance;

import dev.innate.entity.*;
import dev.innate.test.util.Database;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.PersistenceException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HopDaoTest {
    private GenericDao hopDao;

    @BeforeEach
    public void setup() {
        hopDao = new GenericDao(Hop.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getAllHopsSuccess() {
        List<Hop> hops = hopDao.getAll();
        assertEquals(1, hops.size());
    }

    @Test
    void getHopById() {
        Hop hop = (Hop) hopDao.getById(1);
        assertEquals("Citra", hop.getName());
    }

    @Test
    void updateHop() {
        String newHopName = "Cascade";
        Hop hopToUpdate = (Hop) hopDao.getById(1);
        hopToUpdate.setName(newHopName);
        hopDao.update(hopToUpdate);
        Hop retrievedHop = (Hop) hopDao.getById(1);
        assertEquals(newHopName, retrievedHop.getName());
    }

    @Test
    void fkConstraintBlocksHopDelete() {
        Hop hop = (Hop) hopDao.getById(1);
        Assert.assertThrows(PersistenceException.class, () -> hopDao.delete(hop));
    }

    @Test
    void createHop() {
        Hop newHop = new Hop();
        newHop.setId(10);
        newHop.setName("Cascade");
        int id = hopDao.create(newHop);
        Hop newHopRetrieved = (Hop) hopDao.getById(id);
        assertEquals(newHop, newHopRetrieved);
    }
}
