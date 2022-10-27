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
 * @className: Product
 * @author: ycd20
 * @description: product
 * @date: 2022/10/27 21:51
 * @version: 1.0
 */
@Data
@TableName("t_product")
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = -2907409980909070073L;

    /**
     * dataId
     */
    @TableId(value = "id", type = IdType.INPUT)
    @TableField(value = "id", fill = FieldFill.INSERT)
    private Long id;

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
     * product stock
     */
    @TableField("t_product_stock")
    private Integer proStock;

    public Product() {
        this.id = SnowFlakeFactory.getSnowFlakeCache().nextId();
    }


}
