package io.learn.shop.user.service;

import io.learn.shop.bean.User;

/**
 * @projectName: shop
 * @package: io.learn.shop.user.service
 * @className: UserService
 * @author: ycd20
 * @description: user business service
 * @date: 2022/10/27 22:20
 * @version: 1.0
 */
public interface UserService {
    /**
     * get User
     * @param userId id
     * @return User
     */
    User getUserById(Long userId);
}
