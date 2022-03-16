package cn.oddworld.controller;

import cn.oddworld.Jeffchan;
import cn.oddworld.Jeffchan2;
import cn.oddworld.Jeffchan3;
import cn.oddworld.Person;
import cn.oddworld.repository.*;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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
    @Autowired
    private PersonEsDao personEsDao;
    @Autowired
    private JeffchanRepository jeffchanRepository;
    @Autowired
    private Jeffchan3Repository jeffchan3Repository;
    @Autowired
    private Jeffchan2Repository jeffchan2Repository;

    @RequestMapping(value = "jeffchan", method = RequestMethod.GET)
    public ResponseEntity<?> jeffchan(){


        personEsDao.getjeffchan();
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public ResponseEntity<?> hello(){


        for(int i = 0; i< 1000; i++){
            Person person = new Person();
            UUID uuid = UUID.randomUUID();
            person.setName(uuid.toString());
            personRepository.save(person);
        }
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "hello1", method = RequestMethod.GET)
    public ResponseEntity<?> hello1(){



        for(int j = 0; j < 10000; j++){
            List<Jeffchan> jeffchan2List = new ArrayList();
            for(int i = 0; i< 100000; i++){
                Jeffchan jeffchan = new Jeffchan();
                UUID uuid = UUID.randomUUID();
                jeffchan.setName(uuid.toString());
                jeffchan2List.add(jeffchan);
            }
            jeffchanRepository.saveAll(jeffchan2List);
        }
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "hello2", method = RequestMethod.GET)
    public ResponseEntity<?> hello2(){


        for(int j = 0; j < 10000; j++){
            List<Jeffchan2> jeffchan2List = new ArrayList();
            for(int i = 0; i< 100000; i++){
                Jeffchan2 jeffchan2 = new Jeffchan2();
                UUID uuid = UUID.randomUUID();
                jeffchan2.setName(uuid.toString());
                jeffchan2List.add(jeffchan2);
            }
            jeffchan2Repository.saveAll(jeffchan2List);
        }

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "hello3", method = RequestMethod.GET)
    public ResponseEntity<?> hello3(){


        for(int i = 0; i< 1000; i++){
            Jeffchan3 jeffchan3 = new Jeffchan3();
            UUID uuid = UUID.randomUUID();
            jeffchan3.setName(uuid.toString());
            jeffchan3Repository.save(jeffchan3);
        }
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }


    @RequestMapping(value = "getHello", method = RequestMethod.GET)
    public ResponseEntity<?> getHello(){

        return new ResponseEntity<Object>(personRepository.findByName("陈剑辉"), HttpStatus.OK);
    }

    @RequestMapping(value = "getPageHello", method = RequestMethod.GET)
    public ResponseEntity<?> getPageHello(){

        PageRequest pageRequest =  PageRequest.of(1,1,Sort.by(Sort.Direction.DESC, "age"));


        personEsDao.getPersonHighLight();

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

}
