package pers.li.aseckill.web;


import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.li.aseckill.entity.SUser;
import pers.li.aseckill.result.Result;
import pers.li.aseckill.service.SUserService;
import pers.li.aseckill.vo.LoginVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author:luofeng
 * @createTime : 2018/10/9 16:02
 */
@Controller
@RequestMapping("/goods")
public class GoodsController
{
    private static Logger log= LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    SUserService sUserService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;

    /**
     * 可以通过cookie传递参数，也可以通过参数直接传递
     *  --优化注释：组装接收参数SUser替换参数  HttpServletResponse response，
     *   @CookieValue(value = SUserService.TOKEN_COOKIE,required = false) String cookieToken,
     *   @RequestParam(value = SUserService.TOKEN_COOKIE,required = false)String paramToken
     *   @return 返回值自动封装在SUser中
     */
    @RequestMapping("/index_list")
    public String index(Model model,SUser user){
        model.addAttribute("user",user);
        return "goods_list";
    }
     /*
     * 可以通过cookie传递参数，也可以通过参数直接传递
     * @param model
     * @param cookieToken
     * @return
    @RequestMapping("/index_list")
    public String index(Model model,
                        @CookieValue(value = SUserService.TOKEN_COOKIE,required = false) String cookieToken,
                        @RequestParam(value = SUserService.TOKEN_COOKIE,required = false)String paramToken
    ){
        if(StringUtils.isEmpty(cookieToken)&& StringUtils.isEmpty(paramToken)){
            return "login";
        }
        String token=StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        SUser sUser=sUserService.getByToken(response,token);
        model.addAttribute("user",sUser);
        return "goods_list";
    }
*/


    @RequestMapping("/login")
    @ResponseBody
    public Result<Boolean> login(@Valid LoginVo loginVo){
        log.info(loginVo.toString());
        //参数校验
//        String mobile = loginVo.getMobile();
//        String password = loginVo.getPassword();
        //传统校验参数-----------------------------------
//        if(StringUtils.isEmpty(mobile)){
//            return Result.error(CodeMsg.MOBILE_EMPTY);
//        }
//        if(StringUtils.isEmpty(password)){
//            return Result.error(CodeMsg.PASSWORD_EMPTY);
//        }
//        if(!ValidatorUtil.isMobile(mobile)){
//            return Result.error(CodeMsg.MOBILE_ERROR);
//        }
//        CodeMsg codeMsg=sUserService.login(loginVo);
//        if(codeMsg.getCode()==0){
//            return Result.success(true);
//        }else{
//            return Result.error(codeMsg);
//        }
        //全局异常处理优化，参数检验异常直接抛出
        return Result.success(sUserService.login(response,loginVo));
    }



}