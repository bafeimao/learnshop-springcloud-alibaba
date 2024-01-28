package io.learn.shop.product.service.impl;

import io.learn.shop.bean.Product;
import io.learn.shop.product.mapper.ProductMapper;
import io.learn.shop.product.service.ProductService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * @projectName: shop
 * @package: io.learn.shop.product.service.impl
 * @className: ProductServiceImpl
 * @author: ycd20
 * @description: productServiceImpl
 * @date: 2022/10/28 7:22
 * @version: 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper mapper;

    @Override
    public Product getProductById(Long pid) {
        return mapper.selectById(pid);
    }

    @Override
    public int updateProductStockById(Integer count, Long id) {
        return mapper.updateProductStockById(count, id);
    }
}
