package parsing_json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ElementCollection extends ArrayList<Element> {

    public Element findByAtomicNumber(int atomic_number) {
        for( Object e : this){
            if(((Element) e).getNumber() == atomic_number) return (Element) e;
        }
        return null;
    }

    public Element findByName(String name) {
        for( Object e : this){
            if(((Element) e).getName().equals(name)) return (Element) e;
        }
        return null;
    }

    public ElementCollection where(String fieldName, Object value) {
        ElementCollection output = new ElementCollection();
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        ArrayList<String> elementsList = new ArrayList<>();
        for(Element e : this){
            elementsList.add(gson.toJson(e));
        }

        for(String s : elementsList) {
            JsonObject element = parser.parse(s).getAsJsonObject();
            String actualField = element.get(fieldName).getAsString();
            if (actualField.equals(value.toString())) output.add(gson.fromJson(element, Element.class));
        }

        return output;
    }

}
