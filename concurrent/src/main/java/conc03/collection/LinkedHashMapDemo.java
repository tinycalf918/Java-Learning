package conc03.collection;

import java.util.*;

public class LinkedHashMapDemo {

    public static void main(String[] args) {

        System.out.println("test hash map");

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("name1","111");
        hashMap.put("name2","222");
        hashMap.put("name3","333");

        Set<Map.Entry<String, String>> set = hashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        while(iterator.hasNext()){
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("key:" + key + ",value:" + value);
        }

        System.out.println("test linked hash map");
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("name1", "1");
        linkedHashMap.put("name2", "2");
        linkedHashMap.put("name3", "3");

        Set<Map.Entry<String, String>> entries = linkedHashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator1 = entries.iterator();
        while (iterator1.hasNext()){
            Map.Entry<String, String> entry = iterator1.next();
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("key:" + key + ",value:" + value);
        }

    }
}
