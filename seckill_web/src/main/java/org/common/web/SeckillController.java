package org.common.web;

import org.common.anno.KNInterfaceDesc;
import org.common.anno.KNParamDesc;
import org.common.anno.KNProjectDesc;
import org.common.anno.KNProjectName;
import org.common.entity.SeckillBean;
import org.common.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * create by lishengbo on 2018-01-02 17:42
 */
@KNProjectName("项目名称")
@KNProjectDesc("项目描述")
@Controller//放入Spring容器
@RequestMapping("/seckill")
public class SeckillController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @KNInterfaceDesc("接口1")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, @KNParamDesc("年龄") Integer age, @KNParamDesc("名称") String name, int ggg, SeckillBean bean) {
        //列表获取页
        //list.jsp +model=ModelAndView
        List<SeckillBean> seckillList = seckillService.getSeckillList();
        model.addAttribute("list", seckillList);
        return "list";//web-inf/jsp/list.jsp
    }
    @KNInterfaceDesc("接口2")
    @RequestMapping(value = "/list2")
    public String list2(List<String> list) {

        return "list";//web-inf/jsp/list.jsp
    }
    @RequestMapping(value = "/list3")
    @ResponseBody
    @KNInterfaceDesc("接口3")
    public String list2(String name) {
        return "list";//web-inf/jsp/list.jsp
    }
    @RequestMapping(value = "/list4")
    @ResponseBody
    public String list24(String name) {
        return "list";//web-inf/jsp/list.jsp
    }
    @RequestMapping(value = "/list5")
    @ResponseBody
    public String list25(Map<String,Object> map) {
        return "list";//web-inf/jsp/list.jsp
    }

}
