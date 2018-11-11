package com.wxl.dao;

import com.wxl.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 商品持久层入口
 */
public interface IProductDao {

    /**
     * 查询所有商品
     * @return
     * @throws Exception
     */
    @Select("select * from product")
    List<Product> findAll() throws Exception;

    /**
     * 添加商品
     * @param product
     */
    @Insert("insert into " +
            "product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) " +
            "values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice}," +
            "#{productDesc},#{productStatus})")
    void saveProduct(Product product) throws Exception;

    /**
     * 根据id查询商品信息
     * @param productid
     * @return
     */
    @Select("select * from product where id = #{productid}")
    Product findProductById(String productid);
}
