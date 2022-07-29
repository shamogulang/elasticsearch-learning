package cn.oddworld.test;

import org.springframework.stereotype.Component;

@Component
public class TestObj {

    public TestObj(TestObj1 obj1) {
        System.out.println("testobj");
    }
}
