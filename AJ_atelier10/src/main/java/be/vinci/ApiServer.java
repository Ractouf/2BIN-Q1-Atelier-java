package be.vinci;

import be.vinci.api.CdResource;
import jakarta.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class ApiServer {
    private static HttpServer server;

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        URI baseUri =
                UriBuilder.fromUri("http://localhost/").port(9998).build();
        ResourceConfig config = new ResourceConfig();
        config.registerClasses(CdResource.class);
        server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
        System.out.println("API server started on " + baseUri.getHost() + ":" +
                baseUri.getPort());
    }

    public static void stop() {
        server.shutdownNow();
    }
}
