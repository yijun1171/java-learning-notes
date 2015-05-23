package org.yj.apache.lang;

import org.apache.commons.lang3.Range;
import org.junit.Test;

/**
 * Created by yijun.yj on 2015/5/23.
 */
public class RangeTest {
    Range<Item> range = Range.between(new Item(0), new Item(10));

    @Test
    public void containTest(){
        System.out.println("range contains 5 : " + range.contains(new Item(5)));
    }

    @Test
    public void afterTest(){
        System.out.println("range after -1:" + range.isAfter(new Item(-1)));
        System.out.println("range before 12:" + range.isBefore(new Item(12)));
    }


    static class Item implements Comparable<Item>{
        int id;

        public Item(int id) {
            this.id = id;
        }

        public int compareTo(Item item) {
            if(id == item.id)
                return 0;
            return id < item.id ? -1 : 1;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "id=" + id +
                    '}';
        }
    }
}
