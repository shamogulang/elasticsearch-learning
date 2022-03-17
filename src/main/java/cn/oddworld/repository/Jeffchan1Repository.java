package cn.oddworld.repository;

import cn.oddworld.Jeffchan1;
import cn.oddworld.Jeffchan3;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface Jeffchan1Repository extends ElasticsearchRepository<Jeffchan1, String> {

   List<Jeffchan1> findByName(String name);
}
