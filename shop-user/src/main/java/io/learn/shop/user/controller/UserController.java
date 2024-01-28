package io.learn.shop.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.learn.shop.bean.User;
import io.learn.shop.user.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



/**
 * @projectName: shop
 * @package: io.learn.shop.user.controller
 * @className: UserController
 * @author: ycd20
 * @description: UserController
 * @date: 2022/10/27 22:32
 * @version: 1.0
 */
@Slf4j
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private ObjectMapper objectMapper;

    @GetMapping(value = "/get/{uid}")
    public User getUser(@PathVariable("uid") Long uid) throws JsonProcessingException {
        User user = userService.getUserById(uid);
        log.info("get User information is:{}", objectMapper.writeValueAsString(user));
        return user;
    }
}
