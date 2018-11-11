package com.wxl.dao;

import com.wxl.domain.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 商品订单入口
 */
public interface IOrderDao {

    /**
     * 根据订单id查询订单详细信息
     * @return
     */
    @Select("select * from orders where id = #{ordersId}")
    @Results(id = "ordersMap",value = {
        @Result(column = "productid",property = "product",
                one = @One(select ="com.wxl.dao.IProductDao.findProductById")),
        @Result(column = "id",property = "travellers",
                many = @Many(select = "com.wxl.dao.ITravellerDao.findTravellerByOrderId")),
        @Result(column = "MEMBERID",property = "member",
                one = @One(select = "com.wxl.dao.IMemberDao.findMemberById"))
    })
    Orders findOrdersById(String ordersId) throws Exception;


    @Select("select  *  from orders")
    @Results({
            @Result(column = "productid",property = "product",
                    one =@One(select ="com.wxl.dao.IProductDao.findProductById") )
    })
    List<Orders> findAll() throws Exception;
}
