package pers.li.aseckill.rabbitmq;

import lombok.Data;
import pers.li.aseckill.entity.SUser;

/**
 * create by lishengbo 2019/5/27
 */
@Data
public class SeckillMessage {
    private SUser sUser;
    private long goodsId;
}
