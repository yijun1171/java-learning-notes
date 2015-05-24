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
        //fixedValue�ӿ� ֱ�Ӹı䱻���÷����ķ���ֵ
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Hello.class);
        enhancer.setCallback(new FixedValue() {
            //����ص������ķ���ֵһ��Ҫ�ͱ�����ķ����ķ���ֵͬ����
            //������ȷ��������ת��
            public Object loadObject() throws Exception {
                return "hello yijun";
            }
        });
        //���еķ������ö������� ��������ķ���
        Hello hello = (Hello) enhancer.create();
        assertEquals("hello yijun", hello.sayHello("world"));
        assertEquals("hello yijun", hello.toString());
        //final ���εķ������ᱻ���� ͬ��final classҲ���ܱ�����
        assertEquals("goodBey yijun", hello.syaGoodbey("yijun"));
        //������׳�����ת���쳣
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
                    //����������ѭ�� ֤������object�ǲ����Ĵ�����Ķ���
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
                    //������õı������ԭʼ����
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
