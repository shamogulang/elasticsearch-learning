package cn.oddworld.test;

import cn.oddworld.test.anno.MyFeign;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

public class MyHttpClientPathScanner extends ClassPathBeanDefinitionScanner {

    private Class<? extends MyFactoryBean> myFactoryBeanClass = MyFactoryBean.class;

    public MyHttpClientPathScanner(BeanDefinitionRegistry  registry) {
        super(registry, false);
        addIncludeFilter(new AnnotationTypeFilter(MyFeign.class));
    }

    @Override
    protected  Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        processBeanDefinitions(beanDefinitionHolders);
        return beanDefinitionHolders;
    }

    public void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitionHolders){


        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            GenericBeanDefinition beanDefinition = (GenericBeanDefinition)beanDefinitionHolder.getBeanDefinition();
            String beanClassName = beanDefinition.getBeanClassName();
            // 对调用对应bean class的构造器方法
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);
            // beanDefinition.getPropertyValues().add("interfaceClass", definition.getBeanClassName());
            // beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE); //设置与否都能成功
            beanDefinition.setBeanClass(myFactoryBeanClass);
        }

    }

    public void scanPath(String... basePackages){
        doScan(basePackages);
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {

        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }
}
