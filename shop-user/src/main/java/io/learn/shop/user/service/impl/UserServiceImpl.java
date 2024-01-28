package io.learn.shop.user.service.impl;

import io.learn.shop.bean.User;
import io.learn.shop.user.mapper.UserMapper;
import io.learn.shop.user.service.UserService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * @projectName: shop
 * @package: io.learn.shop.user.service.impl
 * @className: UserServiceImpl
 * @author: ycd20
 * @description: user serviceImpl
 * @date: 2022/10/27 22:31
 * @version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper mapper;

    @Override
    public User getUserById(Long userId) {
        return mapper.selectById(userId);
    }
}
