import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class hashmapForStrings {
    final int key;
    String value;

    public hashmapForStrings(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static void insert(HashMap map, String value) {
    }

    public static void main(String args[]) throws IOException {

        /* This is how to declare HashMap */
        HashMap<Integer, String> hmap = new HashMap<Integer, String>();
        String filename = "OfficialScrabbleWordListGerman.txt";
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            System.out.println("adding " + line); //TODO: line in fori
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
// delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();
        //System.out.println(content);


        /*Adding elements to HashMap*/
        hmap.put(12, "Chaitanya");
        hmap.put(2, "Rahul");
        hmap.put(2, "othi");
        hmap.put(7, "Singh");
        hmap.put(49, "Ajeet");
        hmap.put(3, "Anuj");


        /* Display content using Iterator*/
        Set set = hmap.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
            System.out.println(mentry.getValue());
        }

        /* Get values based on key*/
        String var= hmap.get(2);
        System.out.println("Value at index 2 is: "+var);

        /* Remove values based on key*/
        hmap.remove(3);
        System.out.println("Map key and values after removal:");
        Set set2 = hmap.entrySet();
        Iterator iterator2 = set2.iterator();
        while(iterator2.hasNext()) {
            Map.Entry mentry2 = (Map.Entry)iterator2.next();
            System.out.print("Key is: "+mentry2.getKey() + " & Value is: ");
            System.out.println(mentry2.getValue());
        }

    }
}
