package org.common.web;

import org.common.cvo.NBParamVo;
import org.common.entity.SeckillBean;
import org.common.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by lishengbo on 2018-01-02 17:42
 */
@Controller//放入Spring容器
@RequestMapping("/test")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String list(String name) {
        System.err.println("ceshi jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
        return "23";//web-inf/jsp/list.jsp
    }
    @RequestMapping(value = {"/test1"}, method = RequestMethod.GET)
    @ResponseBody
    public Map lis1t(String name,int gg) {
        HashMap<String, String> stringStringHashMap = new HashMap<String, String>();
        stringStringHashMap.put("1", "2");
        stringStringHashMap.put("11", "22");
        System.err.println("ceahsdcasdcasdcasdcasdcd");
        return stringStringHashMap;
    }
    @RequestMapping(value = {"/test2"}, method = RequestMethod.POST)
    @ResponseBody
    public NBParamVo lis1ts(@RequestBody NBParamVo pp) {
        System.err.println(pp.toString());
        return pp;
    }
    @RequestMapping(value = {"/test5"}, method = RequestMethod.POST)
    @ResponseBody
    public NBParamVo lis1sts(@RequestBody NBParamVo pp,String age) {
        System.err.println(pp.toString()+age);
        pp.setCreatedBy(age);
        return pp;
    }
    @RequestMapping(value = {"/test3"}, method = RequestMethod.POST)
    @ResponseBody
    public NBParamVo[] lisw1ts(@RequestBody NBParamVo[] pp) {
        System.err.println(pp.toString());
        return pp;
    }
    @RequestMapping(value = {"/test4"}, method = RequestMethod.POST)
    @ResponseBody
    public List<NBParamVo> lisw1tss(@RequestBody List<NBParamVo> pp) {
        System.err.println(pp.toString());
        return pp;
    }

}
