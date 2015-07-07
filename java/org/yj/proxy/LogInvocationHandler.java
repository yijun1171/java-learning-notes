package org.yj.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * Created by yijun.yj on 2015/5/25.
 */
public class LogInvocationHandler implements InvocationHandler{

    Object target;

    public LogInvocationHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {

        //toString hashCode equals 三个方法也会被代理
        if(method.getName().equals("toString")){
            return target.toString();
        }
        if(method.getName().equals("hashCode")){
            return target.hashCode();
        }
//        if(method.getName().equals("equals")){
//            return proxy.equals(objects[0]);
//        }
        System.out.println("method "+method.getName() + " begin");
        method.invoke(target, objects);
        //method.invoke(o, objects);
        return null;
    }

    public static void main(String[] args){
        LogInvocationHandler handler = new LogInvocationHandler(new OperationImpl());
        Operation op = (Operation) Proxy.newProxyInstance(Operation.class.getClassLoader(),
                new Class[]{Operation.class}, handler);
        op.operation1();
        op.operation2();
        System.out.println(op.toString());
        System.out.println(op.hashCode());
        System.out.println(op.equals(op));
    }
}
