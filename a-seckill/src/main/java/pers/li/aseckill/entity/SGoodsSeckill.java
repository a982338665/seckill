package pers.li.aseckill.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SGoodsSeckill {
	private Long id;
	private Long goodsId;
	private Integer seckillStock;
	private Date startTime;
	private Date endTime;

}
