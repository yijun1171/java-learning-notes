package org.yj.apache.lang;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

/**
 * Created by yijun.yj on 2015/5/23.
 *
 */
public class ArrayUtilTest {

    @Test
    public void UtilTest(){
        Integer[] nullArray = null;
        int len = ArrayUtils.getLength(nullArray);
        System.out.println("len="+len);

        nullArray = ArrayUtils.add(nullArray, 8);
        System.out.println(nullArray[0]);
    }
}
