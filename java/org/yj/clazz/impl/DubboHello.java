package org.yj.clazz.impl;

import org.yj.clazz.service.HelloService;

/**
 * Created by yijun.yj on 2015/7/7.
 */
public class DubboHello implements HelloService {

    public String sayHello() {
        return "Dubblo hello!";
    }
}
