//package com.pigadoor.parsers;
//
//import com.google.gson.*;
//
//import com.pigadoor.data.Chapter;
//
//import java.lang.reflect.Type;
//
///**
// * The {@code ChapterParser} class is responsible for deserializing JSON data into a Chapter object.
// */
//public class ChapterParser implements JsonDeserializer<Chapter> {
//
//    /**
//     * Deserialize JSON data into a Chapter object.
//     *
//     * @param jsonElement                  The JSON element to deserialize.
//     * @param type                         The type of the object.
//     * @param jsonDeserializationContext   The context for JSON deserialization.
//     * @return                             The deserialized Chapter object.
//     * @throws JsonParseException           If an error occurs during JSON parsing.
//     */
//    @Override
//    public Chapter deserialize(JsonElement jsonElement, Type type,
//                               JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
//        JsonObject jsonObject = jsonElement.getAsJsonObject();
//        String chapterName;
//        try {
//            chapterName = jsonObject.get("name").getAsString();
//        } catch (NumberFormatException e) {
//            chapterName = null;
//        }
//        String parentLegion;
//        try {
//            parentLegion = jsonObject.get("parentLegion").getAsString();
//        } catch (NumberFormatException e) {
//            parentLegion = null;
//        }
//        return new Chapter(chapterName, parentLegion);
//    }
//
//}