package cn.oddworld.test.proxy;

import cn.oddworld.test.MyHttpClientPathScanner;
import cn.oddworld.test.anno.MyFeignScanner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyProcessor implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(MyFeignScanner.class);

        Set<String> basePackages = new HashSet<>();
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            MyFeignScanner ann = entry.getValue().getClass().getAnnotation(MyFeignScanner.class);
            basePackages.addAll(Arrays.asList(ann.value()));
        }

        if(basePackages.isEmpty()){
            throw new RuntimeException("MyFeignScanner 没有指定扫描的包！");
        }

        MyHttpClientPathScanner pathScanner = new MyHttpClientPathScanner(beanDefinitionRegistry);

        pathScanner.scanPath(basePackages.toArray(new String[]{}));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

        // 后置处理，do nothing
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
