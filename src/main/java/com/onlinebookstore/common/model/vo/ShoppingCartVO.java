package com.onlinebookstore.common.model.vo;

import com.onlinebookstore.common.model.entity.ShoppingCart;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description Shopping Cart Value Object
 * @author henryzheng
 * @date  2024/09/26
 */
public class ShoppingCartVO extends ShoppingCart {

    public ShoppingCart toEntity() {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(this, shoppingCart);
        return shoppingCart;
    }

    public static ShoppingCartVO fromEntity(ShoppingCart entity) {
        ShoppingCartVO shoppingCartVO = new ShoppingCartVO();
        BeanUtils.copyProperties(entity, shoppingCartVO);
        return shoppingCartVO;
    }

    public static List<ShoppingCartVO> fromEntities(List<ShoppingCart> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return null;
        }
        List<ShoppingCartVO> shoppingCartVOS = new ArrayList<>(entities.size());
        for (ShoppingCart entity : entities) {
            shoppingCartVOS.add(fromEntity(entity));
        }
        return shoppingCartVOS;
    }

}
