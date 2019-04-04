package link.x86.util;

import java.util.HashMap;
import java.util.Map;

public class ObjectUtil {

    public static Map<String,Object> toMap(Object...objs){

        Map<String,Object> map=new HashMap<String,Object>();
        for (int i = 0; i < objs.length; i+=2) {
            map.put(objs[i].toString(),objs[i+1]);
        }
        return map;
    }
}
