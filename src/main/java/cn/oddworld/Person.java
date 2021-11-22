package cn.oddworld;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * Copyright 2021 ChinaMobile Info. Tech Ltd. All rights reserved.
 *
 * @Author: jeffchan
 * @DATE: 2021/11/22 21:39
 **/
@Document(indexName = "person", createIndex = true)
public class Person {

    @Id
    private  String id;
    private int age;
    @Field(analyzer = "ik_max_word")
    private String name;
    private String desc;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
