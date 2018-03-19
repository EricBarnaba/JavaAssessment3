package parsing_json;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

public class ElementCollectionInitializer  {

    public static ElementCollection generate() throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("/Users/ericbarnaba/Dev/test3/src/main/resources/periodic_table.json"));
        ElementCollection elements = new ElementCollection();
        Element[] array = gson.fromJson(reader,Element[].class);
        elements.addAll(Arrays.asList(array));

        return elements;
    }
}
