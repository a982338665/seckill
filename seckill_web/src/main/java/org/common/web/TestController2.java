package org.common.web;

import org.common.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * create by lishengbo on 2018-01-02 17:42
 */
@Controller//放入Spring容器
//@RequestMapping({"/add1","/add2"})
public class TestController2 {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String list(String name,int ss) {
        System.err.println("ceshi jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
        return "23";//web-inf/jsp/list.jsp
    }
//    @RequestMapping(value = {"/test1","test2"}, method = RequestMethod.GET)
//    @ResponseBody
    public Map lis1t(String name) {
        HashMap<String, String> stringStringHashMap = new HashMap<String, String>();
        stringStringHashMap.put("1", "2");
        stringStringHashMap.put("11", "22");
        System.err.println("ceahsdcasdcasdcasdcasdcd");
        return stringStringHashMap;
    }

}
