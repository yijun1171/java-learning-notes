package org.yj.clazz.domain;

/**
 * Created by yijun.yj on 2015/7/17.
 * 测试抽象类
 */
public abstract class AbstractHello {

    public String getThisClass(){
        return this.getClass().getName();
    }
    public abstract String getClassName();

}
