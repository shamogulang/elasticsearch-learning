package cn.oddworld.controller;

import cn.oddworld.Person;
import cn.oddworld.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;

/**
 * Copyright 2021 ChinaMobile Info. Tech Ltd. All rights reserved.
 *
 * @Author: jeffchan
 * @DATE: 2021/11/22 21:55
 **/
@Controller
public class HelloController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public ResponseEntity<?> hello(){


        Person person = new Person();
        person.setAge(11);
        person.setName("陈剑辉");
        person.setDesc("我是真的真好像你");
        personRepository.save(person);

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }


    @RequestMapping(value = "getHello", method = RequestMethod.GET)
    public ResponseEntity<?> getHello(){

        return new ResponseEntity<Object>(personRepository.findByName("陈剑辉"), HttpStatus.OK);
    }

    @RequestMapping(value = "getPageHello", method = RequestMethod.GET)
    public ResponseEntity<?> getPageHello(){

        PageRequest pageRequest =  PageRequest.of(1,1,Sort.by(Sort.Direction.DESC, "age"));

        Page<Person> hh = personRepository.findByAge(11, pageRequest);


        return new ResponseEntity<Object>(hh.getContent(), HttpStatus.OK);
    }

}
