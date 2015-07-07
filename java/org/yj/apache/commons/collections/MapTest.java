package org.yj.apache.commons.collections;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.HashedMap;
import org.junit.Test;


/**
 * Created by yijun.yj on 2015/5/25.
 */
public class MapTest {

    @Test
    public void iterableMapTest(){
        IterableMap map = new HashedMap();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        MapIterator i = map.mapIterator();
        while (i.hasNext()){
            Object key = i.next();
            Object value = i.getValue();
            System.out.println(String.format("key = %s, value = %d", key, value));
        }
    }


}
