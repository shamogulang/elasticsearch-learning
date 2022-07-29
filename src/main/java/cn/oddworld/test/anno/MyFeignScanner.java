package cn.oddworld.test.anno;

import cn.oddworld.test.proxy.MyProcessor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MyProcessor.class)
public @interface MyFeignScanner {


    String value() default  "";
}
