package dev.innate.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.innate.model.Brewery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

public class OpenBreweryClient {
    private final String BASE_URL = "https://api.openbrewerydb.org/breweries";
    private Logger logger = LogManager.getLogger(OpenBreweryClient.class);

    public List<Brewery> getBreweriesByCityState(String city, String state) {
        String parameters = "?by_state=" + state + "&by_city=" + city;
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(BASE_URL + parameters);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Brewery> breweries = null;
        try {
            breweries = Arrays.asList(mapper.readValue(response, Brewery[].class));
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }

        return breweries;
    }
}
