package cn.oddworld.repository;

import cn.oddworld.Jeffchan;
import cn.oddworld.Jeffchan2;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface Jeffchan2Repository  {

   List<Jeffchan2> findByName(String name);

}
