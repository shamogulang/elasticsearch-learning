package cn.oddworld;

import java.util.concurrent.ConcurrentHashMap;

public class TestHasMap {


    public static void main(String[] args) {
        ConcurrentHashMap<String, Person> hashMap = new ConcurrentHashMap<>();

        hashMap.putIfAbsent("chenjianhui",new Person());
        hashMap.putIfAbsent("chenjianhui",new Person());
    }
}
