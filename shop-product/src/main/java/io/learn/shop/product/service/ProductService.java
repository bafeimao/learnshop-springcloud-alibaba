package io.learn.shop.product.service;

import io.learn.shop.bean.Product;

/**
 * @projectName: shop
 * @package: io.learn.shop.product.service
 * @className: ProductService
 * @author: ycd20
 * @description: product service interface
 * @date: 2022/10/28 7:20
 * @version: 1.0
 */
public interface ProductService {
    /**
     * get product by id
     *
     * @param pid pid
     * @return product
     */
    Product getProductById(Long pid);

    /**
     * update product stock
     *
     * @param count
     * @param id
     * @return
     */
    int updateProductStockById(Integer count, Long id);
}
