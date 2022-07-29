package cn.oddworld.test1;

import org.springframework.context.annotation.Profile;

public class TestObj implements TestService{

    public TestObj() {
        System.out.println("testobj");
    }
}
