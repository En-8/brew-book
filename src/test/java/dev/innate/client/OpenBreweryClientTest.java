package dev.innate.client;

import dev.innate.model.Brewery;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class OpenBreweryClientTest {
    private OpenBreweryClient client;

    @BeforeEach
    public void setup() {
        client = new OpenBreweryClient();
    }

    @Test
    public void testGetBreweriesByCityState() throws Exception {
        // Arrange
        Brewery expectedBrewery = new Brewery();
        expectedBrewery.setId(7787);
        expectedBrewery.setCity("Madison");
        expectedBrewery.setState("Wisconsin");
        expectedBrewery.setName("Ale Asylum");

        // Act
        Brewery actualBrewery = client.getBreweriesByCityState("madison", "wisconsin").get(0);

        // Assert
        Assert.assertNotNull(actualBrewery);
        Assert.assertEquals(expectedBrewery, actualBrewery);
    }
}
