package io.learn.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @projectName: shop
 * @package: io.learn.shop.user
 * @className: UserStart
 * @author: ycd20
 * @description: start user service
 * @date: 2022/10/27 22:37
 * @version: 1.0
 */
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@EnableDiscoveryClient
@MapperScan(value = {"io.learn.shop.user.mapper"})
public class UserStarter {
    public static void main(String[] args) {
        SpringApplication.run(UserStarter.class, args);
    }
}
