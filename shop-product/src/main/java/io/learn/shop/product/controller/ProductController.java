package io.learn.shop.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.learn.shop.bean.Product;
import io.learn.shop.product.service.ProductService;
import io.learn.shop.utils.constants.HttpCode;
import io.learn.shop.utils.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * @projectName: shop
 * @package: io.learn.shop.product.controller
 * @className: ProductController
 * @author: ycd20
 * @description: productController
 * @date: 2022/10/28 7:23
 * @version: 1.0
 */
@Slf4j
@RestController
public class ProductController {
    @Resource
    private ProductService service;

    @Resource
    private ObjectMapper mapper;

    @GetMapping(value = "/get/{pid}")
    public Product getProduct(@PathVariable("pid") Long pid) throws JsonProcessingException {
        Product product = service.getProductById(pid);
        log.info(mapper.writeValueAsString(product));
        return product;
    }

    @GetMapping(value = "/update_count/{pid}/{count}")
    public Result<Integer> updateCount(@PathVariable("pid") Long pid, @PathVariable("count") Integer count) {
        log.info("update product stock param is productId:{},count:{}", pid, count);
        int updateCount = service.updateProductStockById(count, pid);
        return new Result<>(HttpCode.SUCCESS, "execute success", updateCount);
    }
}
