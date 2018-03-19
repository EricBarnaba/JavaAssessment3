package parsing_json;

import java.util.ArrayList;

public class ElementCollection extends ArrayList {

    public Element findByAtomicNumber(int atomic_number) {
        for( Object e : this){
            if(((Element) e).getNumber() == atomic_number) return (Element) e;
        }
        return null;
    }

    public Element findByName(String name) {
        return null;
    }

    public ElementCollection where(String fieldName, Object value) {
        return null;
    }
}
