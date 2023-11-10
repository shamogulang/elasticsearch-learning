package cn.oddworld.repository;

import cn.oddworld.Jeffchan;
import cn.oddworld.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface JeffchanRepository // extends ElasticsearchRepository<Jeffchan, String>
{

   List<Jeffchan> findByName(String name);

}
