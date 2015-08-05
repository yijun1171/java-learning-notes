package org.yj.clazz.domain;

/**
 * Created by yijun.yj on 2015/7/17.
 */
public class Hello extends AbstractHello{
    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    public static void main(String[] args){
        Hello hello = new Hello();
        System.out.println(hello.getClassName());
        System.out.println(hello.getThisClass());
    }
}
