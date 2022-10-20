package io.learn.shop.utils.exception;

import io.learn.shop.utils.constants.HttpCode;
import io.learn.shop.utils.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @projectName: shop
 * @package: io.learn.shop.utils.exception
 * @className: RestControllerExceptionHandler
 * @author: ycd20
 * @description: global exception handler
 * @date: 2022/10/20 21:48
 * @version: 1.0
 */
@RestControllerAdvice
public class RestControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(RestControllerExceptionHandler.class);

    /**
     * global exception handler
     */
    @ExceptionHandler(Exception.class)
    public Result<String> handlerException(Exception e) {
        logger.error("server throw error: {}", e);
        return new Result<>(HttpCode.FAILURE, "execute error", e.getMessage());
    }

}
