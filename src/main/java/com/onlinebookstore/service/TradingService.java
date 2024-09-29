package com.onlinebookstore.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @description Centralized management of fund settlement services
 * @author henryzheng
 * @date  2024/09/26
 */
public interface TradingService {

    /**
     * Users can use this interface to checkout specific items added to their shopping cart.
     * They can select one or multiple items for checkout simultaneously.
     * The checkout feature calculates the total number of items purchased and the total amount payable.
     * It also provides a detailed list of checked-out items, along with any invalid cart additions.
     *
     * @param shoppingCartIds The record ID of adding goods to the shopping cart . You can select one or more items.
     * @return Returns the checkout results in JSON format, including total purchase quantity, total purchase amount,
     *         list of purchased items, settled shopping cart items, invalid shopping cart items, etc.
     */
    public JSONObject checkout(List<Integer> shoppingCartIds);

}
