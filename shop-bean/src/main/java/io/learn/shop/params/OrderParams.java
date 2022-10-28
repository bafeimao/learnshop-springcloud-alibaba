package io.learn.shop.params;

import lombok.Data;

/**
 * @projectName: shop
 * @package: io.learn.shop.params
 * @className: OrderParams
 * @author: ycd20
 * @description: order params
 * @date: 2022/10/28 7:56
 * @version: 1.0
 */
@Data
public class OrderParams {
    /**
     * product id
     */
    private Long productId;

    /**
     * user id
     */
    private Long userId;

    /**
     * buy product number
     */
    private Integer count;

    public boolean isEmpty() {
        return (productId == null || productId <= 0) ||
                (userId == null || productId <= 0) ||
                (count == null || count <= 0);
    }
}
