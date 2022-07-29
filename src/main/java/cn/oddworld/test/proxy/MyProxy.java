package cn.oddworld.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MyProxy {

    public static <T> T getProxy(Class<T> myClass, InvocationHandler invoker){

        return (T)Proxy.newProxyInstance(myClass.getClassLoader(), new Class[]{myClass}, invoker);
    }

}
