package org.yj.apache.lang;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by yijun.yj on 2015/5/23.
 */
public class StringUtilsTest {

    @Test
    public void emptyTest(){
        String emptyString = null;
        System.out.println("null == empty :" +  StringUtils.isEmpty(emptyString));
        emptyString = "";
        System.out.println(" \"\" == empty :" +  StringUtils.isEmpty(emptyString));
        emptyString = "    ";
        System.out.println(" \"   \" == empty :" +  StringUtils.isEmpty(emptyString));
    }

    @Test
    public void containTest(){
        String string = "hello world i am a java programmer";
        String subString = "hello";
        System.out.println("only contains \"hello\" :"
                +  StringUtils.containsOnly(string, subString));
        System.out.println("any contains \"cz\" :"
                +  StringUtils.containsAny(string, "cz"));
        System.out.println("not contains \"h\" :"
                +  StringUtils.containsNone(string, "h"));
    }

    @Test
    public void positionSubTest(){
        String longString = "left middle right";
        String left = StringUtils.left(longString, 6);
        System.out.println("left = " + left);
        String middle = StringUtils.mid(longString, 5, 6);
        System.out.println("mid = " + middle);
    }

    @Test
    public void splitTest(){
        String longString  = "left middle right";
        String[] splits = StringUtils.split(longString);
        System.out.println(Arrays.toString(splits));
        longString = StringUtils.join(splits,",");
        System.out.println(longString);
    }

    @Test
    public void checkTest(){
        System.out.println("alpha : "+StringUtils.isAlpha("adfa11"));
        System.out.println("numberic : "+StringUtils.isNumeric("2211"));
    }
}
