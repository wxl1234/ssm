package com.wxl.service;

import com.wxl.domain.Product;

import java.util.List;

/**
 * 商品服务层入口
 */
public interface IProduceService {

    /**
     * 查询所有商品
     * @return
     * @throws Exception
     */
    List<Product> findAll(Integer pageNum,Integer size) throws Exception;

    /**
     * 新增商品
     * @param product
     */
    void saveProduct(Product product) throws Exception;
}
