package io.learn.shop.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.learn.shop.bean.Product;
import org.apache.ibatis.annotations.Param;

/**
 * @projectName: shop
 * @package: io.learn.shop.product.mapper
 * @className: ProductMapper
 * @author: ycd20
 * @description: product mapper
 * @date: 2022/10/28 7:04
 * @version: 1.0
 */
public interface ProductMapper extends BaseMapper<Product> {
    /**
     * minus product stock
     * @param count
     * @param id
     * @return
     */
    int updateProductStockById(@Param("count")Integer count,@Param("id")Long id);
}
