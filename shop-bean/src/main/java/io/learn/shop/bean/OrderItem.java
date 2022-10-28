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
 * @className: OrderItem
 * @author: ycd20
 * @description: order item
 * @date: 2022/10/27 21:56
 * @version: 1.0
 */
@Data
@TableName("t_order_item")
public class OrderItem implements Serializable {

    @Serial
    private static final long serialVersionUID = -132917393755780293L;

    /**
     * dataId
     */
    @TableId(value = "id", type = IdType.INPUT)
    @TableField(value = "id", fill = FieldFill.INSERT)
    private Long id;

    /**
     * orderId
     */
    @TableField("t_order_id")
    private Long orderId;

    /**
     * productId
     */
    @TableField("t_product_id")
    private Long productId;

    /**
     * productName
     */
    @TableField("t_product_name")
    private String productName;

    /**
     * product price
     */
    @TableField("t_product_price")
    private BigDecimal productPrice;

    /**
     * buy number
     */
    @TableField("t_number")
    private Integer number;

    public OrderItem() {
        this.id = SnowFlakeFactory.getSnowFlakeCache().nextId();
    }

}
