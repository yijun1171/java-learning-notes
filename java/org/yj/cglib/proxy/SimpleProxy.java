package org.yj.cglib.proxy;

import net.sf.cglib.proxy.*;
import org.junit.Before;
import org.junit.Test;
import org.yj.cglib.target.Hello;

import java.lang.reflect.Method;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

/**
 * Created by yijun.yj on 2015/5/24.
 */
public class SimpleProxy {


    @Test
    public void fixedValueTest(){
        //fixedValue接口 直接改变被调用方法的返回值
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Hello.class);
        enhancer.setCallback(new FixedValue() {
            //这个回调方法的返回值一定要和被代理的方法的返回值同类型
            //才能正确进行类型转换
            public Object loadObject() throws Exception {
                return "hello yijun";
            }
        });
        //所有的方法调用都被拦截 包括父类的方法
        Hello hello = (Hello) enhancer.create();
        assertEquals("hello yijun", hello.sayHello("world"));
        assertEquals("hello yijun", hello.toString());
        //final 修饰的方法不会被代理 同理final class也不能被代理
        assertEquals("goodBey yijun", hello.syaGoodbey("yijun"));
        //这里会抛出类型转换异常
        //hello.hashCode()
    }

    @Test
    public void invokationHandlerTest(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Hello.class);
        enhancer.setCallback(new InvocationHandler() {
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

                if(method.getDeclaringClass() != Object.class &&
                        method.getReturnType() ==  String.class) {
                    System.out.println("method begin");
                    //这里会产生死循环 证明参数object是产生的代理类的对象
                    //String result = (String) method.invoke(o, objects);
                    return "hello world";
                }else {
                    throw new RuntimeException("don't know what to do");
                }
            }
        });
        Hello hello = (Hello) enhancer.create();
        assertEquals("hello world", hello.sayHello("world"));
    }

    @Test
    public void methodInterceptorTest(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Hello.class);
        enhancer.setCallback(new MethodInterceptor() {

            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if (method.getDeclaringClass() != Object.class &&
                        method.getReturnType() == String.class) {
                    System.out.println("method begin");
                    //这里调用的被代理的原始方法
                    return methodProxy.invokeSuper(o, objects);
                } else {
                    return methodProxy.invokeSuper(o, objects);
                }
            }
        });
        Hello hello = (Hello) enhancer.create();
        assertEquals("hello world", hello.sayHello("world"));
    }
}
