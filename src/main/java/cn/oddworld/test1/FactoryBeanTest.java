package cn.oddworld.test1;

import org.springframework.beans.factory.FactoryBean;

public class FactoryBeanTest implements FactoryBean {

    @Override
    public Class<?> getObjectType() {
        return Testxx2.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public Object getObject() throws Exception {

        return new Testxx2();
    }
}
