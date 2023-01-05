package be.vinci.api;

import be.vinci.services.ClassAnalyzer;
import jakarta.json.JsonStructure;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

/**
 * Send class data to make class diagrams
 * The class name must be given, and present into the "classes" package
 */
@Path("classes")
public class Classes {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonStructure getClassInfo(@QueryParam("classname") String classname) {
        ClassAnalyzer analyzer;
        try {
            analyzer = new ClassAnalyzer(Class.forName("be.vinci.classes." + classname));
        } catch (ClassNotFoundException e) {
            throw new WebApplicationException(404);
        }
        return analyzer.getFullInfo();
    }
}
