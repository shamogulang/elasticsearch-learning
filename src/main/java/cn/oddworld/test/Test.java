package cn.oddworld.test;

import cn.oddworld.test.anno.MyFeign;
import cn.oddworld.test.proxy.MyProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class Test {


    public static void main(String[] args) {


//        MyInvoker myInvoker = new MyInvoker();
//
//        MyAno1 proxy = MyProxy.getProxy(MyAno1.class, myInvoker);
//
//        proxy.test();

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("cn.oddworld.test");

//        applicationContext.registerBean("pro", MyProcessor.class);
//        MyHttpClientPathScanner myHttpClientPathScanner = new MyHttpClientPathScanner(applicationContext);
//        //applicationContext.refresh();
//        myHttpClientPathScanner.doScan("cn.oddworld.test");
//      Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(MyFeign.class);
//        MyFactoryBean bean = (MyFactoryBean) applicationContext.getBean("&myAno1");
//        MyFactoryBean bean1 = (MyFactoryBean) applicationContext.getBean("&myAno2");


        TestObj bean = applicationContext.getBean(TestObj.class);

        System.out.println("");
//        int scan = myPathScanner.scan("cn.oddworld.test");
//        System.out.println(scan);
//        applicationContext.refresh();
//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        Arrays.asList(beanDefinitionNames).stream().forEach(item -> System.out.println(item));
    }



}
