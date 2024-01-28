package io.learn.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @projectName: shop
 * @package: io.learn.shop
 * @className: ProductStart
 * @author: ycd20
 * @description: product service starter
 * @date: 2022/10/28 7:04
 * @version: 1.0
 */
@SpringBootApplication
@MapperScan(value = {"io.learn.shop.product.mapper"})
@EnableTransactionManagement(proxyTargetClass = true)
@EnableDiscoveryClient
public class ProductStarter {
    public static void main(String[] args) {
        SpringApplication.run(ProductStarter.class, args);
    }
}
