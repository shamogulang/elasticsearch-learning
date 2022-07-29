package cn.oddworld.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvoker implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 其他默认的方法，可以直接过滤掉 toString等等
        if(method.getName().equals("toString")){
            return method.invoke(this, args);
        }
        //或者你的方法是有注解的
        //if(!method.isAnnotationPresent(MyMethodAnno.class)){
            //return method.invoke(this, args);
        //}

        System.out.println("proxy-hello");
        System.out.println(method.getName());
        return  "需要返回的结果";
    }
}
