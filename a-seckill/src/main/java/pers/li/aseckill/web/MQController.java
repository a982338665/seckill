package pers.li.aseckill.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.li.aseckill.entity.Test;
import pers.li.aseckill.rabbitmq.MQSender;
import pers.li.aseckill.redis.RedisService;
import pers.li.aseckill.redis.service.TestKey;
import pers.li.aseckill.result.CodeMsg;
import pers.li.aseckill.result.Result;
import pers.li.aseckill.service.TestService;

import java.util.Map;

/**
 * @author:luofeng
 * @createTime : 2018/10/9 16:02
 */
@Controller
@RequestMapping("/mq")
public class MQController
{

    @Autowired
    MQSender mqSender;

    @RequestMapping("/send")
    @ResponseBody
    public Result<String> error(){
        mqSender.send("hello mq!");
        return Result.error(CodeMsg.SESSION_ERROR);
    }

    @RequestMapping("/sendTopic")
    @ResponseBody
    public Result<String> sendTopic(){
        mqSender.sendTopic("hello topic mq!");
        return Result.error(CodeMsg.SESSION_ERROR);
    }

    @RequestMapping("/sendFanout")
    @ResponseBody
    public Result<String> sendFanout(){
        mqSender.sendFanout("hello fanout mq!");
        return Result.error(CodeMsg.SESSION_ERROR);
    }
    @RequestMapping("/sendHeader")
    @ResponseBody
    public Result<String> sendHeader(){
        mqSender.sendHeaders("hello header mq!");
        return Result.error(CodeMsg.SESSION_ERROR);
    }

}