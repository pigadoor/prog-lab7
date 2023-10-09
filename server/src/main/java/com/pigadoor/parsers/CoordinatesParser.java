//package com.pigadoor.parsers;
//
//import com.google.gson.JsonDeserializationContext;
//import com.google.gson.JsonDeserializer;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParseException;
//
//import com.pigadoor.data.Coordinates;
//
//import java.lang.reflect.Type;
//
///**
// * The {@code CoordinatesParser} class is responsible for deserializing JSON data into a Coordinates object.
// */
//public class CoordinatesParser implements JsonDeserializer<Coordinates> {
//
//    /**
//     * Deserialize JSON data into a Coordinates object.
//     *
//     * @param jsonElement                  The JSON element to deserialize.
//     * @param type                         The type of the object.
//     * @param jsonDeserializationContext   The context for JSON deserialization.
//     * @return                             The deserialized Coordinates object.
//     * @throws JsonParseException           If an error occurs during JSON parsing.
//     */
//    @Override
//    public Coordinates deserialize(JsonElement jsonElement, Type type,
//                                   JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
//        JsonObject jsonObject = jsonElement.getAsJsonObject();
//        Double x;
//        try {
//            x = jsonObject.get("x").getAsDouble();
//        } catch (NumberFormatException e) {
//            x = null;
//        }
//        Float y;
//        try {
//            y = jsonObject.get("y").getAsFloat();
//        } catch (NumberFormatException e) {
//            y = null;
//        }
//        return new Coordinates(x, y);
//    }
//
//}