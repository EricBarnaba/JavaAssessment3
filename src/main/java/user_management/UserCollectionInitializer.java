package user_management;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

public class UserCollectionInitializer {
    public static UserCollection generate() throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("/Users/ericbarnaba/Dev/test3/src/main/resources/users.json"));
        UserCollection users = new UserCollection();
        User[] array = gson.fromJson(reader,User[].class);
        users.addAll(Arrays.asList(array));

        return users;
    }
}
