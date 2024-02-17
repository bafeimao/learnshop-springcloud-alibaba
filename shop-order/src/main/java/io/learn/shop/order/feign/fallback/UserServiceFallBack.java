package io.learn.shop.order.feign.fallback;

import io.learn.shop.bean.User;
import io.learn.shop.order.feign.UserService;
import org.springframework.stereotype.Component;

/**
 * @author ycd20
 */
@Component
public class UserServiceFallBack implements UserService {
    @Override
    public User getUser(Long uid) {
        User user = new User();
        user.setId(-1L);
        return user;
    }
}