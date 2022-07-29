package cn.oddworld.test1;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Profile("dev")
@Service
public class Testxx {

    public Testxx() {
        System.out.println("Testxx");
    }
}
