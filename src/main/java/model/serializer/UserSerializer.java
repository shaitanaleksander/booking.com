package model.serializer;

import com.google.gson.*;
import model.User;
import org.bson.types.ObjectId;

import java.lang.reflect.Type;

public class UserSerializer implements JsonSerializer<User>,JsonDeserializer<User> {

    @Override
    public JsonElement serialize(User user,
                                 Type type,
                                 JsonSerializationContext jsonSerializationContext
    ) {
        JsonObject object = new JsonObject();
        object.addProperty("id", user.getId().toHexString());
        object.addProperty("name", user.getName());
        object.addProperty("password", user.getPassword());
        object.addProperty("role", user.getRole());
        return object;
    }

    @Override
    public User deserialize(JsonElement json,
                            Type type,
                            JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        var user = new User();
        user.setId(new ObjectId(object.get("id").getAsString()));
        user.setName(object.get("name").getAsString());
        user.setPassword(object.get("password").getAsString());
        user.setRole(object.get("role").getAsString());
        return user;
    }
}
