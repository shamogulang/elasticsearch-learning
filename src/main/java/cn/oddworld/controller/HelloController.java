package cn.oddworld.controller;

import cn.oddworld.Jeffchan;
import cn.oddworld.Person;
import cn.oddworld.repository.JeffchanRepository;
import cn.oddworld.repository.PersonEsDao;
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
    @Autowired
    private PersonEsDao personEsDao;
    @Autowired
    private JeffchanRepository jeffchanRepository;

    @RequestMapping(value = "jeffchan", method = RequestMethod.GET)
    public ResponseEntity<?> jeffchan(){


        personEsDao.getjeffchan();
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public ResponseEntity<?> hello(){


        for(int i = 0; i< 1000; i++){
            Person person = new Person();
            person.setName("周杰伦首次亮相幕前，是在1997年8月，当时是因为高中同学参加TVBS-G的选秀节目《超级新人王》而为朋友担任钢琴伴奏。虽然他同学歌唱未获奖，该节目主持人吴宗宪却对周杰伦的乐谱作曲工整印象深刻，认为其有潜力进而签约发掘进入乐坛。吴宗宪非常肯定周杰伦的才华，所以先让他专注作曲的历练后再出唱片，虽写过多首歌曲不过仍有不少曲目未被他人采用而退稿，例如：刘德华《眼泪知道》和张惠妹《忍者》。吴宗宪对周杰伦很");
            personRepository.save(person);
        }
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "hello1", method = RequestMethod.GET)
    public ResponseEntity<?> hello1(){


        for(int i = 0; i< 1000; i++){
            Jeffchan jeffchan = new Jeffchan();
            jeffchan.setName("周杰伦首次亮相幕前，是在1997年8月，当时是因为高中同学参加TVBS-G的选秀节目《超级新人王》而为朋友担任钢琴伴奏。虽然他同学歌唱未获奖，该节目主持人吴宗宪却对周杰伦的乐谱作曲工整印象深刻，认为其有潜力进而签约发掘进入乐坛。吴宗宪非常肯定周杰伦的才华，所以先让他专注作曲的历练后再出唱片，虽写过多首歌曲不过仍有不少曲目未被他人采用而退稿，例如：刘德华《眼泪知道》和张惠妹《忍者》。吴宗宪对周杰伦很");
            jeffchanRepository.save(jeffchan);
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
