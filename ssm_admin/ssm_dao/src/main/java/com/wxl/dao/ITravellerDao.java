package com.wxl.dao;

import com.wxl.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 游客持久层入口
 */
public interface ITravellerDao {

    /**
     * 根据订单编号，查询用户(可能是组团购买)
     * @param id
     * @return
     */
    @Select("select * from traveller where id in(" +
            "select TRAVELLERID  from ORDER_TRAVELLER where ORDERID = #{id})")
    List<Traveller> findTravellerByOrderId(String id);
}
