package pers.li.aseckill.redis.service;

import pers.li.aseckill.redis.BasePrefix;

public class SUserKey extends BasePrefix {

	//有效期两天
	public static final int TOKEN_EXPIRE = 3600*24 * 2;

	private SUserKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}
	public static SUserKey token = new SUserKey(TOKEN_EXPIRE, "tk");
}
