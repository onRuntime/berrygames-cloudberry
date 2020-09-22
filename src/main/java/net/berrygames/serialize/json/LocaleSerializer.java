package net.berrygames.serialize.json;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Locale;

public class LocaleSerializer implements JsonSerializer<Locale>, JsonDeserializer<Locale> {

    @Override
    public JsonElement serialize(Locale src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }

    @Override
    public Locale deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}
