package io.learn.shop.order.feign;

import io.learn.shop.bean.User;
import io.learn.shop.order.feign.fallback.UserServiceFallBack;
import io.learn.shop.order.feign.fallback.factory.UserServiceFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author YouChuande
 */
//@FeignClient(value = "server-user",fallback = UserServiceFallBack.class)
@FeignClient(value = "server-user",fallbackFactory = UserServiceFallBackFactory.class)
public interface UserService {
    @GetMapping(value = "/user/get/{uid}")
    User getUser(@PathVariable("uid") Long uid);

}
