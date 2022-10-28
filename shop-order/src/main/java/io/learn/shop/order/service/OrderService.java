package io.learn.shop.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.learn.shop.params.OrderParams;

/**
 * @projectName: shop
 * @package: io.learn.shop.order.service
 * @className: OrderService
 * @author: ycd20
 * @description: order service
 * @date: 2022/10/28 7:33
 * @version: 1.0
 */
public interface OrderService {
    /**
     * save order
     * @param orderParams
     */
    void saveOrder(OrderParams orderParams) throws JsonProcessingException;


}
