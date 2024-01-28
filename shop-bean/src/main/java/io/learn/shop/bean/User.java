package io.learn.shop.bean;

import com.baomidou.mybatisplus.annotation.*;
import io.learn.shop.utils.id.SnowFlakeFactory;
import io.learn.shop.utils.password.PasswordUtils;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @projectName: shop
 * @package: io.learn.shop.bean
 * @className: User
 * @author: ycd20
 * @description: User dto
 * @date: 2022/10/27 21:44
 * @version: 1.0
 */
@Data
@TableName("t_user")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = -7032479567987350240L;

    /**
     * dataId
     */
    @TableId(value = "id", type = IdType.INPUT)
    @TableField(value = "id", fill = FieldFill.INSERT)
    private Long id;

    /**
     * username
     */
    @TableField("t_username")
    private String username;

    /**
     * password
     */
    @TableField("t_password")
    private String password;

    /**
     * phone number
     */
    @TableField("t_phone")
    private String phone;

    /**
     * address
     */
    @TableField("t_address")
    private String address;

    public User(){
        this.id = SnowFlakeFactory.getSnowFlakeCache().nextId();
        this.password = PasswordUtils.getPassword("123456");
    }
}

