package io.learn.shop.utils.password;

import io.learn.shop.utils.md5.Md5Hash;

/**
 * @projectName: shop
 * @package: io.learn.shop.utils.password
 * @className: PasswordUtils
 * @author: ycd20
 * @description: password Utils
 * @date: 2022/10/27 22:07
 * @version: 1.0
 */
public class PasswordUtils {
    public static String getPassword(String password){
        if (password == null || password.trim().isEmpty()) {
            return password;
        }
        for (int i = 0; i < 5; i++) {
            password = Md5Hash.md5Java(password);
        }
        return password;
    }
}
