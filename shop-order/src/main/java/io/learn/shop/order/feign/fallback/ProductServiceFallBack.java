package io.learn.shop.order.feign.fallback;

import io.learn.shop.bean.Product;
import io.learn.shop.order.feign.ProductService;
import io.learn.shop.utils.response.Result;
import org.springframework.stereotype.Component;

/**
 * @author ycd20
 */
@Component
public class ProductServiceFallBack implements ProductService {
    @Override
    public Product getProduct(Long pid) {
        Product product = new Product();
        product.setId(-1L);
        return product;
    }

    @Override
    public Result<Integer> updateCount(Long pid, Integer count) {
        Result<Integer> result = new Result<>();
        result.setCode(1001);
        result.setCodeMsg("触发了容错逻辑");
        return result;
    }
}