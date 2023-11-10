package cn.oddworld.repository;

import cn.oddworld.Jeffchan3;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface Jeffchan3Repository {

   List<Jeffchan3> findByName(String name);
}
