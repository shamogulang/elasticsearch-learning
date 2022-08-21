package cn.oddworld.controller;

import cn.oddworld.*;
import cn.oddworld.repository.*;
import cn.oddworld.test.MyAno1;
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

import java.util.*;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private Jeffchan1Repository jeffchan1Repository;
    @Autowired
    private Jeffchan4Repository jeffchan4Repository;
    @Autowired
    private Jeffchan5Repository jeffchan5Repository;

    @Autowired
    private MyAno1 myAno1;

    private byte[] hello = new byte[1024*1024*2];

    @RequestMapping(value = "te", method = RequestMethod.GET)
    public ResponseEntity<?> jeffchan1(){

        myAno1.test();
        myAno1.toString();

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }


    @RequestMapping(value = "jeffchan", method = RequestMethod.GET)
    public ResponseEntity<?> jeffchan() throws InterruptedException {

        TimeUnit.SECONDS.sleep(5L);

        personEsDao.getjeffchan();
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "jeffchan", method = RequestMethod.GET, headers = {"X-API-VERSION=v2"})
    public ResponseEntity<?> jeffchanV2(){

        System.out.println("版本2");
        personEsDao.getjeffchan();
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }



    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public ResponseEntity<?> hello(){


        Random random = new Random();
        for(int j = 0; j < 100; j++){
            List<Jeffchan> jeffchan2List = new ArrayList();
            for(int i = 0; i< 100000; i++){
                Jeffchan jeffchan = new Jeffchan();
                UUID uuid = UUID.randomUUID();
                jeffchan.setName(uuid.toString());
                jeffchan.setTime(random.nextLong());
                jeffchan2List.add(jeffchan);
            }
            jeffchanRepository.saveAll(jeffchan2List);
        }
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }


    @RequestMapping(value = "hello1", method = RequestMethod.GET)
    public ResponseEntity<?> hello1(){

        for(int j = 0; j < 100; j++){
            List<Jeffchan1> jeffchan2List = new ArrayList();
            for(int i = 0; i< 100000; i++){
                Jeffchan1 jeffchan = new Jeffchan1();
                UUID uuid = UUID.randomUUID();
                jeffchan.setName(uuid.toString());
                jeffchan2List.add(jeffchan);
            }
            jeffchan1Repository.saveAll(jeffchan2List);
        }
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "hello4", method = RequestMethod.GET)
    public ResponseEntity<?> hello4(){

        for(int j = 0; j < 100; j++){
            List<Jeffchan4> jeffchan2List = new ArrayList();
            for(int i = 0; i< 100000; i++){
                Jeffchan4 jeffchan = new Jeffchan4();
                UUID uuid = UUID.randomUUID();
                jeffchan.setName(uuid.toString());
                jeffchan2List.add(jeffchan);
            }
            jeffchan4Repository.saveAll(jeffchan2List);
        }
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "hello2", method = RequestMethod.GET)
    public ResponseEntity<?> hello2(){


        for(int j = 0; j < 100; j++){
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

        for(int j = 0; j < 100; j++){
            List<Jeffchan3> jeffchan3List = new ArrayList();
            for(int i = 0; i< 100000; i++){
                Jeffchan3 jeffchan3 = new Jeffchan3();
                UUID uuid = UUID.randomUUID();
                jeffchan3.setName(uuid.toString());
                jeffchan3List.add(jeffchan3);
            }
            jeffchan3Repository.saveAll(jeffchan3List);
        }
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "hello5", method = RequestMethod.GET)
    public ResponseEntity<?> hello5(){

        for(int j = 0; j < 100; j++){
            List<Jeffchan5> jeffchan5List = new ArrayList();
            for(int i = 0; i< 100000; i++){
                Jeffchan5 jeffchan5 = new Jeffchan5();
                UUID uuid = UUID.randomUUID();
                jeffchan5.setName(uuid.toString());
                jeffchan5List.add(jeffchan5);
            }
            jeffchan5Repository.saveAll(jeffchan5List);
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
