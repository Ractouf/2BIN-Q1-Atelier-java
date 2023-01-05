package be.vinci.services;

import jakarta.json.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Class analyzer. It saves a class into attribute, from a constructor, and
 * gives a lot of convenient methods to transform this into a JSON object
 * to print the UML diagram.
 */
public class ClassAnalyzer {

    private Class aClass;

    public ClassAnalyzer(Class aClass) {
        this.aClass = aClass;
    }

    /**
     * Create a JSON Object with all the info of the class.
     * @return
     */
    public JsonObject getFullInfo() {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        objectBuilder.add("name", aClass.getSimpleName());
        objectBuilder.add("fields", getFields());
        objectBuilder.add("methods", getMethods());

        return objectBuilder.build();
    }

    /**
     * Get fields, and create a Json Array with all fields data.
     * Example :
     * [ {}, {} ]
     */
    public JsonArray getFields() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (Field field : aClass.getDeclaredFields()) {
            arrayBuilder.add(getField(field));
        }

        return arrayBuilder.build();
    }

    public JsonArray getMethods() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (Method method : aClass.getDeclaredMethods()) {
            arrayBuilder.add(getMethod(method));
        }

        return arrayBuilder.build();
    }

    /**
     * Get a field, and create a Json Object with all field data.
     * Example :
     * {
     * name: "firstname",
     * type: "String",
     * visibility : "private"  // public, private, protected, package
     * isStatic: false,
     * }
     */
    public JsonObject getField(Field f) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        objectBuilder.add("name", f.getName());
        objectBuilder.add("type", f.getType().getSimpleName());
        objectBuilder.add("visibility", getFieldVisibility(f));
        objectBuilder.add("isStatic", isFieldStatic(f));

        return objectBuilder.build();
    }

    public JsonObject getMethod(Method method) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        objectBuilder.add("name", method.getName());
        objectBuilder.add("returnType", method.getReturnType().getSimpleName());
        objectBuilder.add("parameters", getParameters(method));
        objectBuilder.add("visibility", getMethodVisibility(method));
        objectBuilder.add("isStatic", Modifier.isStatic(method.getModifiers()));
        objectBuilder.add("isAbstract", Modifier.isAbstract(method.getModifiers()));

        return objectBuilder.build();
    }

    private JsonArray getParameters(Method m) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (Parameter p : m.getParameters()) {
            arrayBuilder.add(p.getType().getSimpleName());
        }

        return arrayBuilder.build();
    }

    /**
     * Return whether a field is static or not
     *
     * @param f the field to check
     * @return true if the field is static, false else
     */
    private boolean isFieldStatic(Field f) {
        return Modifier.isStatic(f.getModifiers());
    }

    /**
     * Get field visibility in a string form
     *
     * @param f the field to check
     * @return the visibility (public, private, protected, package)
     */
    private String getFieldVisibility(Field f) {
        if (Modifier.isPublic(f.getModifiers()))
            return "Public";
        if (Modifier.isPrivate(f.getModifiers()))
            return "Private";
        if (Modifier.isProtected(f.getModifiers()))
            return "Protected";
        return "Package";
    }

    private String getMethodVisibility(Method m) {
        if (Modifier.isPublic(m.getModifiers()))
            return "Public";
        if (Modifier.isPrivate(m.getModifiers()))
            return "Private";
        if (Modifier.isProtected(m.getModifiers()))
            return "Protected";
        return "Package";
    }
}
