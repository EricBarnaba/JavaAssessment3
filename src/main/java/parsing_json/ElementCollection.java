package parsing_json;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
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
        //JsonReader reader = new JsonReader(new FileReader("/Users/ericbarnaba/Dev/test3/src/main/resources/periodic_table.json"));
        String elementsText = gson.toJson(this);
        String[] elementsArray = elementsText.split(",");
        Pattern field = Pattern.compile("(" +fieldName+ ")([a-zA-Z|\\d]+)");
        for(String s : elementsArray) {
            Matcher matcher = field.matcher(s);
            if(matcher.find()) {
                if (matcher.group(2).equals(value)) {
                    output.add(gson.fromJson(s, Element.class));
                }
            }
        }
        return output;
    }

}
