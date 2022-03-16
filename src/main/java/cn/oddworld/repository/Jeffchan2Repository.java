package cn.oddworld.repository;

import cn.oddworld.Jeffchan;
import cn.oddworld.Jeffchan2;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface Jeffchan2Repository extends ElasticsearchRepository<Jeffchan2, String> {

   List<Jeffchan2> findByName(String name);

}
