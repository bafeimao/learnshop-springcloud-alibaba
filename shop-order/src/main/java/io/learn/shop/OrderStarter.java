package io.learn.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @projectName: shop
 * @package: io.learn.shop
 * @className: OrderStart
 * @author: ycd20
 * @description: Order Starter
 * @date: 2022/10/28 7:31
 * @version: 1.0
 */
@SpringBootApplication
@MapperScan(value = {"io.learn.shop.order.mapper"})
@EnableTransactionManagement(proxyTargetClass = true)
@EnableDiscoveryClient
public class OrderStarter {
    public static void main(String[] args) {
        SpringApplication.run(OrderStarter.class, args);
    }
}
