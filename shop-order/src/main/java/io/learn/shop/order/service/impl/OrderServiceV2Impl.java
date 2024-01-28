package io.learn.shop.order.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

/**
 * @author YouChuande
 */
@Service
@Slf4j
public class OrderServiceV2Impl implements OrderService {
    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderItemMapper orderItemMapper;

    private final String userServer = "server-user";
    private final String productServer = "server-product";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(OrderParams orderParams) throws JsonProcessingException {
        String userUrl = getServiceUrl(userServer);
        String productUrl = getServiceUrl(productServer);
        User user = restTemplate.getForObject(userUrl + "/user/get/" + orderParams.getUserId(), User.class);
        if (user == null) {
            throw new RuntimeException("can not get user information:" + JSONObject.toJSONString(orderParams));
        }
        Product product = restTemplate.getForObject(productUrl + "/product/get/" + orderParams.getProductId(), Product.class);
        if (product == null) {
            throw new RuntimeException("can not get product information:" + JSONObject.toJSONString(orderParams));
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
        Result<Integer> result = restTemplate.getForObject(productUrl + "/product/update_count/" +
                orderParams.getProductId() + "/" + orderParams.getCount(), Result.class);
        if (result.getCode() != HttpCode.SUCCESS) {
            throw new RuntimeException("stock minus failed");
        }
        log.info("stock minus success");
    }

    private String getServiceUrl(String serviceName) {
        return discoveryClient.getInstances(serviceName).get(0).getUri().toString();
    }
}
