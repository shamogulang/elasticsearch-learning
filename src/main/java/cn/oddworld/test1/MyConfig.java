package cn.oddworld.test1;

import org.springframework.context.annotation.*;

@Import(ImportBeanDefinitionRegistrarTest.class)
public class MyConfig {

    public MyConfig() {
        System.out.println();
    }

    //@Bean
//    @Conditional(value = MyCondition2.class)
//    @Bean
//    public Testxx2 testxx2(){
//
//        return new Testxx2();
//    }

//    @Bean
////    @ConditionalOnMissingBean
////    @Profile("dev")
//    @ConditionalOnMissingClass(value = "cn.oddworld.test1.TestService1")
//    public TestService testObj1(){
//
//        return new TestObj();
//    }
}
