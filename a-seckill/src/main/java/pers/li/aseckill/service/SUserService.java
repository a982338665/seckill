package pers.li.aseckill.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pers.li.aseckill.dao.SUserDao;
import pers.li.aseckill.dao.TestDao;
import pers.li.aseckill.entity.SUser;
import pers.li.aseckill.entity.Test;
import pers.li.aseckill.exception.GlobalException;
import pers.li.aseckill.redis.RedisService;
import pers.li.aseckill.redis.service.SUserKey;
import pers.li.aseckill.result.CodeMsg;
import pers.li.aseckill.utils.HttpServletUtil;
import pers.li.aseckill.utils.MD5Util;
import pers.li.aseckill.utils.UUIDUtil;
import pers.li.aseckill.vo.LoginVo;
import pers.li.aseckill.web.GoodsController;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author:luofeng
 * @createTime : 2018/10/10 9:31
 */
@Service
public class SUserService {

    private static Logger log= LoggerFactory.getLogger(SUserService.class);
    public static final String TOKEN_COOKIE="token";

    @Autowired
    SUserDao sUserDao;
    @Autowired
    RedisService redisService;

    public SUser getById(long mobile){
        return sUserDao.getUserById(mobile);
    }

    public String login(HttpServletResponse response,LoginVo loginVo) {
        log.info("------------------------->");
        if(loginVo==null){
            throw new GlobalException( CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //判断手机号是否存在
        SUser byId = getById(Long.parseLong(mobile));
        if(byId==null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbpass=byId.getPassword();
        String dbsalt=byId.getSalt();
        String toDBPass = MD5Util.formPassToDBPass(password, dbsalt);
        if(!dbpass.equals(toDBPass)){
            throw new GlobalException( CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie,有效期设置为与session一致
        String token = UUIDUtil.uuid();
        addCookie(response,token,byId);
        return token;


    }

    private void addCookie(HttpServletResponse response,String token,SUser byId) {
        log.info("==================》");
        redisService.set(SUserKey.token,token,byId);
        Cookie cookie = new Cookie(TOKEN_COOKIE,token);
        cookie.setMaxAge(SUserKey.token.getExpireSeconds());
        cookie.setPath("/");
//        HttpServletResponse response = HttpServletUtil.getHttpServletObject(HttpServletResponse.class);
        response.addCookie(cookie);
    }

    public SUser getByToken(HttpServletResponse response,String token) {
        if(StringUtils.isEmpty(token)){
            return null;
        }
        SUser sUser = redisService.get(SUserKey.token, token, SUser.class);
        if(sUser!=null){
            //延长token有效期--新建token--使用原有token
            addCookie(response, token,sUser);
        }
        return sUser;
    }
}
