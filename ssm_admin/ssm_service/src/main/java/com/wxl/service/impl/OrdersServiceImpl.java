package com.wxl.service.impl;

import com.github.pagehelper.PageHelper;
import com.wxl.dao.IOrderDao;
import com.wxl.domain.Orders;
import com.wxl.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单服务层实现类
 */
@Service("ordersService")
public class OrdersServiceImpl implements IOrdersService {


    @Autowired
    private IOrderDao orderDao;

    /**
     * 分页查询
     * @param pageSize
     * @param size
     * @return
     * @throws Exception
     */
    @Override
    public List<Orders> findOrdersPage(Integer pageSize, Integer size) throws Exception {
        PageHelper.startPage(pageSize,size);
        return orderDao.findAll();
    }

    /**
     * 订单详情查询
     * @param ordersId
     * @return
     * @throws Exception
     */
    @Override
    public Orders findOrdersById(String ordersId) throws Exception {
        return orderDao.findOrdersById(ordersId);
    }
}
