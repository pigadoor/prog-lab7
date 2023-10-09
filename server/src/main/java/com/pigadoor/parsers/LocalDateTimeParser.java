//package com.pigadoor.parsers;
//
//import com.google.gson.JsonDeserializationContext;
//import com.google.gson.JsonDeserializer;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonParseException;
//
//import java.lang.reflect.Type;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Locale;
//
///**
// * The {@code LocalDateTimeParser} class is responsible for deserializing JSON data into a LocalDateTime object.
// */
//public class LocalDateTimeParser implements JsonDeserializer<LocalDateTime> {
//
//    /**
//     * Deserialize JSON data into a LocalDateTime object.
//     *
//     * @param jsonElement                  The JSON element to deserialize.
//     * @param type                         The type of the object.
//     * @param context                      The context for JSON deserialization.
//     * @return                             The deserialized LocalDateTime object.
//     * @throws JsonParseException           If an error occurs during JSON parsing.
//     */
//    @Override
//    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
//            throws JsonParseException {
//        return LocalDateTime.parse(json.getAsString(),
//                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS").withLocale(Locale.ENGLISH));
//    }
//
//}
