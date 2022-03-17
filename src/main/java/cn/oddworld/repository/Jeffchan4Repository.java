package cn.oddworld.repository;

import cn.oddworld.Jeffchan1;
import cn.oddworld.Jeffchan4;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface Jeffchan4Repository extends ElasticsearchRepository<Jeffchan4, String> {

   List<Jeffchan4> findByName(String name);
}
