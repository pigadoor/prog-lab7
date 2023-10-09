//package com.pigadoor.parsers;
//
//import com.google.gson.*;
//import com.pigadoor.data.Chapter;
//import com.pigadoor.data.Coordinates;
//import com.pigadoor.data.SpaceMarine;
//
//import java.lang.reflect.Type;
//import java.util.LinkedList;
//
///**
// * The {@code SpaceMarinesParser} class is responsible for deserializing JSON data into a list of SpaceMarine objects.
// */
//public class SpaceMarinesParser implements JsonDeserializer<LinkedList<SpaceMarine>> {
//
//    /**
//     * Deserialize JSON data into a list of SpaceMarine objects.
//     *
//     * @param json                         The JSON element to deserialize.
//     * @param type                         The type of the object.
//     * @param jsonDeserializationContext   The context for JSON deserialization.
//     * @return                             The deserialized list of SpaceMarine objects.
//     * @throws JsonParseException           If an error occurs during JSON parsing.
//     */
//    @Override
//    public LinkedList<SpaceMarine> deserialize(JsonElement json, Type type, JsonDeserializationContext
//            jsonDeserializationContext) throws JsonParseException {
//        LinkedList<SpaceMarine> linkedList = new LinkedList<>();
//        JsonArray jsonArray = json.getAsJsonArray();
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(SpaceMarine.class, new SpaceMarineParser())
//                .registerTypeAdapter(Chapter.class, new ChapterParser())
//                .registerTypeAdapter(Coordinates.class, new CoordinatesParser())
//                .registerTypeAdapter(LinkedList.class, new SpaceMarinesParser()).create();
//        for (JsonElement element : jsonArray) {
//            SpaceMarine spaceMarine = gson.fromJson(element, SpaceMarine.class);
//            linkedList.add(spaceMarine);
//        }
//        return linkedList;
//    }
//
//}