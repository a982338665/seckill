package pers.li.aseckill.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import pers.li.aseckill.dao.SGoodDao;
import pers.li.aseckill.entity.SOrderInfo;
import pers.li.aseckill.entity.SOrderSeckill;
import pers.li.aseckill.entity.SUser;
import pers.li.aseckill.redis.RedisService;
import pers.li.aseckill.vo.SGoodsVo;

import java.util.List;

/**
 * @author:luofeng
 * @createTime : 2018/10/10 9:31
 */
@Service
public class SeckillService {

    private static Logger log= LoggerFactory.getLogger(SeckillService.class);

    @Autowired
    SGoodService sGoodService;
    @Autowired
    SOrderService sOrderService;

    /**
     *   手动回滚事务
     *   TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
     * @param user
     * @param goods
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public SOrderInfo seckill(SUser user, SGoodsVo goods) {
        //减库存，下订单，写入秒杀订单---最好将逻辑写在相对应的service中，注入service
        sGoodService.reduceStock(goods);
        //s_order_info s_order_seckill
        return sOrderService.createOrder(user,goods);

    }
}
