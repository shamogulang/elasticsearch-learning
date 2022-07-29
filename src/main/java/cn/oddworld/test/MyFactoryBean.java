package cn.oddworld.test;

import cn.oddworld.test.proxy.MyInvoker;
import cn.oddworld.test.proxy.MyProxy;
import org.springframework.beans.factory.FactoryBean;


public class MyFactoryBean<T> implements FactoryBean<T> {

    private Class<T> proxyClass;
    private MyInvoker myInvoker = new MyInvoker();


    public MyFactoryBean(Class<T> proxyClass) {
        this.proxyClass = proxyClass;
    }

    public MyFactoryBean() {
    }

    @Override
    public T getObject() throws Exception {

        return MyProxy.getProxy(this.proxyClass, myInvoker);
    }

    @Override
    public Class<?> getObjectType() {

        return proxyClass;
    }

    @Override
    public boolean isSingleton() {

        return true;
    }
}
