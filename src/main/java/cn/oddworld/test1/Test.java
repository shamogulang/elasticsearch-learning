package cn.oddworld.test1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {



    public static void main(String[] args) throws ClassNotFoundException {

//        Class<?> aClass = Class.forName("cn.oddworld.test1.TestService", false, Test.class.getClassLoader());
//        System.out.println(aClass);

        Logger logger = LoggerFactory.getLogger(Test.class);

        System.setProperty("spring.profiles.active", "dev");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(FactoryBeanTest.class);


        Testxx2 bean = applicationContext.getBean(Testxx2.class);
        Testxx2 bean1 = applicationContext.getBean(Testxx2.class);

        System.out.println(bean);
//        System.out.println(bean);

    }



}
