package pers.li.aseckill.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.li.aseckill.dao.SGoodDao;
import pers.li.aseckill.redis.RedisService;

/**
 * @author:luofeng
 * @createTime : 2018/10/10 9:31
 */
@Service
public class SGoodService {

    private static Logger log= LoggerFactory.getLogger(SGoodService.class);

    @Autowired
    SGoodDao sGoodDao;
    @Autowired
    RedisService redisService;

}
