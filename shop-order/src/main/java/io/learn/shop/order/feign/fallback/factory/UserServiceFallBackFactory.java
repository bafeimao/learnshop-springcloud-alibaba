package io.learn.shop.order.feign.fallback.factory;

import io.learn.shop.bean.User;
import io.learn.shop.order.feign.UserService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFallBackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable cause) {
        return uid -> {
            User user = new User();
            user.setId(-1L);
            return user;
        };
    }
}