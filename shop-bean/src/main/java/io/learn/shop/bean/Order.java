package io.learn.shop.bean;

import com.baomidou.mybatisplus.annotation.*;
import io.learn.shop.utils.id.SnowFlakeFactory;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @projectName: shop
 * @package: io.learn.shop.bean
 * @className: Order
 * @author: ycd20
 * @description: order
 * @date: 2022/10/28 7:35
 * @version: 1.0
 */
@Data
@TableName("t_order")
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = -2907409980909070073L;

    /**
     * data id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @TableField(value = "id", fill = FieldFill.INSERT)
    private Long id;

    /**
     * user id
     */
    @TableField("t_user_id")
    private Long userId;

    /**
     * username
     */
    @TableField("t_user_name")
    private String username;

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

    /**
     * product price
     */
    @TableField("t_total_price")
    private BigDecimal totalPrice;

    public Order() {
        this.id = SnowFlakeFactory.getSnowFlakeCache().nextId();
    }


}
