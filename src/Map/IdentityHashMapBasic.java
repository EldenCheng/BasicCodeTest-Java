package Map;

import java.util.*;

public class IdentityHashMapBasic {

    public static void main(String[] args) {
        IdentityHashMap<String, Integer> ihm = new IdentityHashMap<>();

        //下面两行代码向IdentityHashMap对象添加两个key-value对
        ihm.put(new String("Yuwen"),89);
        ihm.put(new String("Yuwen"),78);
        String s = new String("Yuwen");
        ihm.put(s,58);
        //下面两行代码只会向IdentityHashMap对象添加一个key-value对
        ihm.put("java",93);
        ihm.put("java",98);
        //int r1 = ihm.get("语文");
        //int r2 = ihm.get("java");
        Set<Map.Entry<String, Integer>> e = ihm.entrySet();
        //e.contains("Yuwen");
        //Object[] o = e.toArray();

        Object[] keys = ihm.keySet().toArray();
        Object[] values = ihm.values().toArray();
        System.out.println(ihm);
        System.out.println(ihm.getOrDefault("Yuwen",0));
        System.out.println(ihm.keySet().contains("Yuwen"));
        System.out.println(ihm.keySet().contains(s));
        System.out.println(keys[0]);
        System.out.println(values[0]);
        //System.out.println(o[0]);

        //IdentityHashMap<String, List> map1 = new IdentityHashMap<>();
        IdentityHashMap<String, IdentityHashMap> map1 = new IdentityHashMap<>();
        IdentityHashMap<String, IdentityHashMap> map2 = new IdentityHashMap<>();
        IdentityHashMap<String, String> map3 = new IdentityHashMap<>();
        map3.put("ID","peter");
        map2.put("3", map3);
        map1.put("1", new IdentityHashMap());
        System.out.println(map1);
        System.out.println(map1.containsKey("1"));
        System.out.println(map2.containsKey("3"));

    }

}
