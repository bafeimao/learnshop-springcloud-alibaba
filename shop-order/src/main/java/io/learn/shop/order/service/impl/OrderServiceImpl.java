package io.learn.shop.order.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.learn.shop.bean.Order;
import io.learn.shop.bean.OrderItem;
import io.learn.shop.bean.Product;
import io.learn.shop.bean.User;
import io.learn.shop.order.mapper.OrderItemMapper;
import io.learn.shop.order.mapper.OrderMapper;
import io.learn.shop.order.service.OrderService;
import io.learn.shop.params.OrderParams;
import io.learn.shop.utils.constants.HttpCode;
import io.learn.shop.utils.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @projectName: shop
 * @package: io.learn.shop.order.service.impl
 * @className: OrderServiceImpl
 * @author: ycd20
 * @description: order service impl
 * @date: 2022/10/28 7:59
 * @version: 1.0
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private ObjectMapper mapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderItemMapper orderItemMapper;
    @Resource
    private RestTemplate restTemplate;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(OrderParams orderParams) throws JsonProcessingException {
        if (orderParams.isEmpty()) {
            throw new RuntimeException("param exception: " + mapper.writeValueAsString(orderParams));
        }
        User user = restTemplate.getForObject("http://localhost:8060/user/get/" + orderParams.getUserId(), User.class);
        if (user == null) {
            throw new RuntimeException("can not get user information:" +
                    mapper.writeValueAsString(orderParams));
        }
        Product product = restTemplate.getForObject("http://localhost:8070/product/get/" +
                orderParams.getProductId(), Product.class);
        if (product == null) {
            throw new RuntimeException("can not get product information:" +
                    mapper.writeValueAsString(orderParams));
        }
        if (product.getProductStock() < orderParams.getCount()) {
            throw new RuntimeException("product stock less:" +
                    mapper.writeValueAsString(orderParams));
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

        Result<Integer> result = restTemplate.getForObject("http://localhost:8070/product/update_count/" +
                orderParams.getProductId() + "/" + orderParams.getCount(), Result.class);
        if (result.getCode() != HttpCode.SUCCESS) {
            throw new RuntimeException("stock minus failed");
        }
        log.info("stock minus success");
    }
}
