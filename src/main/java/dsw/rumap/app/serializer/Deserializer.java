package dsw.rumap.app.serializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import dsw.rumap.app.maprepository.composite.MapNode;

import java.lang.reflect.Type;

public class Deserializer implements JsonDeserializer<MapNode> {
    @Override
    public MapNode deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}
