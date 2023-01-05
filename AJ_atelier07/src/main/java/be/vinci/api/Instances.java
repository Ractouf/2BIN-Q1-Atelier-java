package be.vinci.api;

import be.vinci.services.InstancesAnalyzer;
import be.vinci.utils.InstanceGraphBuilder;
import jakarta.json.JsonStructure;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Send instances graph data to make object diagrams
 * The instances graphs are initialized by a class containing the "initInstanceGraph" method,
 * building the instance graph, and returning it.
 * The "instance builder class name" must be given and present into the "instances" package
 */
@Path("instances")
public class Instances {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonStructure getInstanceGraphInfo(@QueryParam("builderclassname") String builderClassname) {
        InstancesAnalyzer analyzer;
        Class<?> foundClass;

        try {
            foundClass = Class.forName("be.vinci.instances." + builderClassname);

        } catch (ClassNotFoundException e) {
            throw new WebApplicationException(404);
        }

        try {
            for (Method m : foundClass.getDeclaredMethods()) {
                if (m.isAnnotationPresent(InstanceGraphBuilder.class)) {
                    analyzer = new InstancesAnalyzer(m.invoke(foundClass.newInstance()));
                    return analyzer.getFullInfo();
                }
            }

        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
