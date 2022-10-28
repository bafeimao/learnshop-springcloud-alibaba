package io.learn.shop.order.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.learn.shop.order.mapper.OrderItemMapper;
import io.learn.shop.order.mapper.OrderMapper;
import io.learn.shop.order.service.OrderService;
import io.learn.shop.params.OrderParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

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
    public void saveOrder(OrderParams orderParams) {

    }
}
