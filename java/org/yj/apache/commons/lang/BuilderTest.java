package org.yj.apache.commons.lang;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.Test;

import java.util.Objects;

/**
 * Created by yijun.yj on 2015/5/23.
 */
public class BuilderTest {

    @Test
    public void compareBuilder(){

    }

    static class Item implements Comparable<Item>{
        int id;
        int age;

        public Item(int id, int age) {
            this.id = id;
            this.age = age;
        }


        public int compareTo(Item item) {
            return new CompareToBuilder()
                    .append(id, item.id)
                    .append(age, item.age)
                    .toComparison();
        }

        @Override
        public int hashCode(){
            return new HashCodeBuilder()
                    .append(id).append(age)
                    .toHashCode();
        }

        @Override
        public boolean equals(Object object){
            if(object == null){return false;}
            if(object == this){return true;}
            if (object.getClass() != getClass()){return false;}

            Item i = (Item) object;
            return new EqualsBuilder()
                    .append(id, i.id)
                    .append(age, i.age)
                    .isEquals();
        }
    }
}
