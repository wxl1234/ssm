package com.wxl.service.impl;

import com.github.pagehelper.PageHelper;
import com.wxl.dao.IProductDao;
import com.wxl.domain.Product;
import com.wxl.service.IProduceService;
import com.wxl.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("produceService")
public class ProductServiceImpl implements IProduceService {

    @Autowired
    private IProductDao productDao;

    /**
     * 查询所有商品信息
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> findAll(Integer pageNum,Integer size) throws Exception {
        PageHelper.startPage(pageNum,size);
        return productDao.findAll();
    }

    /**
     * 新增商品
     * @param product
     */
    @Override
    public void saveProduct(Product product) throws Exception {
        product.setId(UuidUtil.getUuid());
        productDao.saveProduct(product);
    }


}
