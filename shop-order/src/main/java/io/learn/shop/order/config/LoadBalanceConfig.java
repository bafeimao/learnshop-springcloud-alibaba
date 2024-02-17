package io.learn.shop.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @projectName: shop
 * @package: io.learn.shop.order.config
 * @className: LoadBalanceConfig
 * @author: ycd20
 * @description: load balance
 * @date: 2022/10/28 8:02
 * @version: 1.0
 */
@Component
public class LoadBalanceConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
