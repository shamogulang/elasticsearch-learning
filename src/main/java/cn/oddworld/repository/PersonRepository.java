package cn.oddworld.repository;

import cn.oddworld.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PersonRepository extends ElasticsearchRepository<Person, String> {

   List<Person> findByName(String name);

   Page<Person>  findByAge(int age, Pageable pageable);
}
