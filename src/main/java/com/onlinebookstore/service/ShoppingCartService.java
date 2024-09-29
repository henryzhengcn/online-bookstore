package com.onlinebookstore.service;

import com.onlinebookstore.common.model.vo.ShoppingCartVO;

import java.util.List;

/**
 * @description Centralized management of shopping cart services
 * @author henryzheng
 * @date  2024/09/26
 */
public interface ShoppingCartService {

    /**
     * Users can use this interface to add their preferred items to their shopping cart.
     * If the selected item is not already in the cart, it will be added directly.
     * However, if the item is already present in the cart, the quantity will be updated by adding the selected amount to the existing quantity.
     * This approach enhances the user experience by simplifying the shopping process. This logic is commonly used on major e-commerce platforms.
     *
     * @param bookVO The ID and quantity of the goods added to the cart
     */
    public void add(ShoppingCartVO bookVO);

    /**
     * Query the list of goods added to shopping cart
     *
     * @return List of goods added to the shopping cart
     */
    public List<ShoppingCartVO> findAll();

}
