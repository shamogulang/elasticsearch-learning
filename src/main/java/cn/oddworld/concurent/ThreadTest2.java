package cn.oddworld.concurent;

import org.springframework.util.CollectionUtils;

import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ThreadTest2 {

    public static void main(String[] args) {

        String routingValue = "my-long-routing-value";
        String encodedRoutingValue = Base64.getEncoder().encodeToString(routingValue.getBytes());
    }
}
