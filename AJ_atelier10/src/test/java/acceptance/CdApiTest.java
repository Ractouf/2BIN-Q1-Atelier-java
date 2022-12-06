package acceptance;

import be.vinci.ApiServer;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CdApiTest {
    @Test
    void findCdInStockFromTitle() {
        String expectedResponse = "title:Young Mystic, artist:Bob Marley, stock:3, price:12.0€, id:666-777";
        String actualResponse = getRequest("cds", "title=young mystic");
        assertEquals(expectedResponse, actualResponse);
    }

    private String getRequest(String path, String queryParam1) {
        Client c = ClientBuilder.newClient();
        WebTarget target = c.target("http://localhost:9998/");
        String key = queryParam1.split("=")[0];
        String value = queryParam1.split("=")[1];
        String responseBody = target.path(path).queryParam(key, value)
                .request().get(String.class);
        return responseBody;
    }

    @BeforeEach
    void setUp() {
        ApiServer.start();
    }

    @AfterEach
    void tearDown() {
        ApiServer.stop();
    }
}
