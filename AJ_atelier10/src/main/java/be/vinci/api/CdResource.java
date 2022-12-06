package be.vinci.api;

import be.vinci.domain.CompactDisc;
import be.vinci.services.Catalog;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/cds")
public class CdResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String findCds(@DefaultValue("") @QueryParam("title") String title,
                          @DefaultValue("") @QueryParam("artist") String artist) {
        System.out.println("find my CD ? " + title + " artist");
        Catalog catalog = new Catalog();
        List<CompactDisc> cdsFound = catalog.findCds(title, artist);
        return cdsFound.toString();
    }
}
