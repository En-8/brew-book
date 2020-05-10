package dev.innate.persistance;

import dev.innate.entity.Style;
import dev.innate.test.util.Database;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.PersistenceException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StyleDaoTest {
    private GenericDao styleDao;

    @BeforeEach
    public void setup() {
        styleDao = new GenericDao(Style.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getAllStylesSuccess() {
        List<Style> styles = styleDao.getAll();
        assertEquals(1, styles.size());
    }

    @Test
    void getStyleById() {
        Style style = (Style) styleDao.getById(1);
        assertEquals("Farmhouse Ale", style.getName());
    }

    @Test
    void updateStyle() {
        String newStyleName = "Hefeweizen";
        Style styleToUpdate = (Style) styleDao.getById(1);
        styleToUpdate.setName(newStyleName);
        styleDao.update(styleToUpdate);
        Style retrievedStyle = (Style) styleDao.getById(1);
        assertEquals(newStyleName, retrievedStyle.getName());
    }

    @Test
    void fkConstraintBlocksDeleteStyle() {
        Style style = (Style) styleDao.getById(1);
        Assert.assertThrows(PersistenceException.class, () -> styleDao.delete(style));
    }

    @Test
    void createStyle() {
        Style newStyle = new Style();
        newStyle.setId(10);
        newStyle.setName("Hefeweizen");
        int id = styleDao.create(newStyle);
        Style newStyleRetrieved = (Style) styleDao.getById(id);
        assertEquals(newStyle, newStyleRetrieved);
    }
}
