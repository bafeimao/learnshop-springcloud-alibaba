package io.learn.shop.order.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.learn.shop.bean.Order;
import io.learn.shop.bean.OrderItem;
import io.learn.shop.bean.Product;
import io.learn.shop.bean.User;
import io.learn.shop.order.feign.ProductService;
import io.learn.shop.order.feign.UserService;
import io.learn.shop.order.mapper.OrderItemMapper;
import io.learn.shop.order.mapper.OrderMapper;
import io.learn.shop.order.service.OrderService;
import io.learn.shop.params.OrderParams;
import io.learn.shop.utils.constants.HttpCode;
import io.learn.shop.utils.response.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author YouChuande
 */
@Service("orderServiceV6")
@Slf4j
public class OrderServiceV6Impl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderItemMapper orderItemMapper;


    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(OrderParams orderParams) throws JsonProcessingException {

        User user = userService.getUser(orderParams.getUserId());
        if (user == null) {
            throw new RuntimeException("未获取到用户信息: " + JSONObject.toJSONString(orderParams));
        }
        if (user.getId() == -1) {
            throw new RuntimeException("触发了用户微服务的容错逻辑: " + JSONObject.toJSONString(orderParams));
        }
        Product product = productService.getProduct(orderParams.getProductId());
        if (product == null) {
            throw new RuntimeException("未获取到商品信息: " + JSONObject.toJSONString(orderParams));
        }
        if (product.getId() == -1) {
            throw new RuntimeException("触发了商品微服务的容错逻辑: " + JSONObject.toJSONString(orderParams));
        }
        if (product.getProductStock() < orderParams.getCount()) {
            throw new RuntimeException("product stock less:" +
                    JSONObject.toJSONString(orderParams));
        }

        Order order = new Order();
        order.setAddress(user.getAddress());
        order.setPhone(user.getPhone());
        order.setUserId(user.getId());
        order.setUsername(user.getUsername());
        order.setTotalPrice(product.getProductPrice().multiply(BigDecimal.valueOf(orderParams.getCount())));
        orderMapper.insert(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setNumber(orderParams.getCount());
        orderItem.setProductId(orderParams.getProductId());
        orderItem.setOrderId(order.getId());
        orderItem.setProductName(product.getProductName());
        orderItem.setProductPrice(product.getProductPrice());
        orderItemMapper.insert(orderItem);
        Result<Integer> result = productService.updateCount(orderParams.getProductId(), orderParams.getCount());

        if (result.getCode() == 1001) {
            throw new RuntimeException("触发了商品微服务的容错逻辑: " + JSONObject.toJSONString(orderParams));
        }
        if (result.getCode() != HttpCode.SUCCESS) {
            throw new RuntimeException("库存扣减失败");
        }
        log.info("stock minus success");
    }
}
