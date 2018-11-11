package com.wxl.service;

import com.wxl.domain.Orders;

import java.util.List;

/**
 * 订单服务层入口
 */
public interface IOrdersService {

    /**
     * 分页查询订单信息
     * @param pageSize
     * @param size
     * @return
     */
    List<Orders> findOrdersPage(Integer pageSize,Integer size) throws Exception;


    /**
     * 根据订单编号查询订单详情
     * @param ordersId
     * @return
     * @throws Exception
     */
    Orders findOrdersById(String ordersId) throws Exception;
}
