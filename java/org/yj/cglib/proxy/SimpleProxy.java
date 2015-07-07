package org.yj.cglib.proxy;

import net.sf.cglib.proxy.*;
import org.junit.Test;
import org.yj.cglib.target.Hello;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by yijun.yj on 2015/5/24.
 */
public class SimpleProxy {


    @Test
    public void fixedValueTest(){
        //fixedValue??? ????????��?????????
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Hello.class);
        enhancer.setCallback(new FixedValue() {
            //??????????????????????????????????????????
            //??????????????????
            public Object loadObject() throws Exception {
                return "hello yijun";
            }
        });
        //???��??????????????? ????????????
        Hello hello = (Hello) enhancer.create();
        assertEquals("hello yijun", hello.sayHello("world"));
        assertEquals("hello yijun", hello.toString());
        //final ???��???????????? ???final class??????????
        assertEquals("goodBey yijun", hello.syaGoodbey("yijun"));
        //?????????????????
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
                    //????????????? ???????object????????????????
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
                    //????????????????????
                    return methodProxy.invokeSuper(o, objects);
                } else {
                    return methodProxy.invokeSuper(o, objects);
                }
            }
        });
        Hello hello = (Hello) enhancer.create();
        assertEquals("hello world", hello.sayHello("world"));
    }

    @Test
    public void testCallbackFilter() throws Exception {
        Enhancer enhancer = new Enhancer();
        CallbackHelper callbackHelper = new CallbackHelper(Hello.class, new Class[0]) {
            @Override
            protected Object getCallback(Method method) {
                if(method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return new FixedValue() {
                        public Object loadObject() throws Exception {
                            return "Hello cglib!";
                        }
                    };
                } else {
                    return NoOp.INSTANCE; // A singleton provided by NoOp.
                }
            }
        };
        enhancer.setSuperclass(Hello.class);
        enhancer.setCallbackFilter(callbackHelper);
        enhancer.setCallbacks(callbackHelper.getCallbacks());
        Hello proxy = (Hello) enhancer.create();
        assertEquals("Hello cglib!", proxy.sayHello(null));
        assertNotEquals("Hello cglib!", proxy.toString());
        proxy.hashCode(); // Does not throw an exception or result in an endless loop.
    }
}
