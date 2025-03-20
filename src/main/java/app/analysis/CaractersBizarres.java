package analysis;

import java.util.ArrayList;
import java.util.List;

public class CaractersBizarres {

    public static List<Character> mapChar(char c) {
        List<Character> result = new ArrayList<>();
        switch (c) {
            case 'î':
                result.add('^');
                result.add('i');
                break;
            case 'û':
                result.add('^');
                result.add('u');
                break;
            case 'â':
                result.add('^');
                result.add('a');
                break;
            case 'ê':
                result.add('^');
                result.add('e');
                break;
            //etc
            default:
                result.add(c);
        }
        return result;
    }
}
