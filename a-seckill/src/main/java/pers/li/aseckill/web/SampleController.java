package pers.li.aseckill.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author:luofeng
 * @createTime : 2018/10/9 16:02
 */
@Controller
@RequestMapping("/demo")
public class SampleController
{

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","luofeng--22222222222.111111111111111111");
        return "hello";
    }
}
/**/