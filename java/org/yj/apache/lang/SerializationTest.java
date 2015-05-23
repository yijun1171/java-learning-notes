package org.yj.apache.lang;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

import java.io.Serializable;

/**
 * Created by yijun.yj on 2015/5/23.
 */
public class SerializationTest {

    @Test
    public void test(){
        byte[] bytes = SerializationUtils.serialize(new Item(22,"test"));
        Item item = SerializationUtils.deserialize(bytes);
        System.out.println(item);

        Item newItem = SerializationUtils.roundtrip(item);
        System.out.println("old Item == new Item ?" + (newItem == item));
    }



    static class Item implements Serializable{
        int id;
        String name;
        transient int tel = 111;
        public Item(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
