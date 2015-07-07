package org.yj.clazz;

import org.junit.Test;
import org.yj.clazz.service.HelloService;

import java.util.ServiceLoader;

/**
 * Created by yijun.yj on 2015/7/7.
 */
public class ServiceLoaderTest {

    @Test
    public void test(){
        ServiceLoader<HelloService> serviceLoader = ServiceLoader.load(HelloService.class);
        for(HelloService service : serviceLoader){
            System.out.println(service.sayHello());
        }
    }
}
