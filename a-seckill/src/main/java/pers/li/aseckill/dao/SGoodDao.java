package pers.li.aseckill.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.li.aseckill.entity.SUser;
import pers.li.aseckill.vo.SGoodsVo;

import java.util.List;

/**
 * @author:luofeng
 * @createTime : 2018/10/10 9:26
 */
@Mapper
public interface SGoodDao {

    @Select("select * from s_user where id=#{id}")
    List<SGoodsVo> getSGoodsVo();

}
