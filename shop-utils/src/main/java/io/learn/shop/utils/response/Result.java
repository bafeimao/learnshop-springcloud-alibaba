package io.learn.shop.utils.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @projectName: shop
 * @package: io.learn.shop.utils.response
 * @className: Result
 * @author: ycd20
 * @description: result
 * @date: 2022/10/20 21:53
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1497405107265595284L;

    /**
     * status code
     */
    private Integer code;

    /**
     * status description
     */
    private String codeMsg;

    /**
     * response data
     */
    private T data;
}
