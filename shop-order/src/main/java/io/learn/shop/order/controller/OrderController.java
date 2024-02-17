package io.learn.shop.order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.learn.shop.order.service.OrderService;
import io.learn.shop.params.OrderParams;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: shop
 * @package: io.learn.shop.order.controller
 * @className: OrderController
 * @author: ycd20
 * @description: order controller
 * @date: 2022/10/28 8:02
 * @version: 1.0
 */
@Slf4j
@RestController
public class OrderController {
    @Resource
    @Qualifier(value = "orderServiceV6")
    private OrderService orderService;
    @Resource
    private ObjectMapper objectMapper;

    @GetMapping(value = "/submit_order")
    public String submitOrder(OrderParams orderParams) throws JsonProcessingException {
        log.info("submit order params:{}", objectMapper.writeValueAsString(orderParams));
        orderService.saveOrder(orderParams);
        return "success";
    }

    @GetMapping(value = "/concurrent_request")
    public String concurrentRequest(){
        log.info("测试业务在高并发场景下是否存在问题");
        return "binghe";
    }

    @GetMapping(value = "/test_sentinel")
    public String testSentinel(){
        log.info("测试Sentinel");
        return "sentinel";
    }
}
