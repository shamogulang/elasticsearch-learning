package cn.oddworld.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Person {

    @Value("10")
    private int name;


    public Person() {
        System.out.println("person construct...");
    }
}
