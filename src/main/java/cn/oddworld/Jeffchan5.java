package cn.oddworld;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "jeffchan5", createIndex = true)
public class Jeffchan5 {

    private String name;
    @Id
    private  String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
