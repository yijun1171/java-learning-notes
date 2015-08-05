package org.yj.clazz.classLoader;

/**
 * Created by yijun.yj on 2015/7/21.
 */
public class ClassLoaderTest {

    public static void main(String[] args){
        ClassLoader cl = ClassLoaderTest.class.getClassLoader();

        while(cl != null){
            System.out.println(cl);
            cl = cl.getParent();
        }
    }
}
