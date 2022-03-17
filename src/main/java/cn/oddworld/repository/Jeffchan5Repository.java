package cn.oddworld.repository;

import cn.oddworld.Jeffchan1;
import cn.oddworld.Jeffchan5;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface Jeffchan5Repository extends ElasticsearchRepository<Jeffchan5, String> {

   List<Jeffchan5> findByName(String name);
}
