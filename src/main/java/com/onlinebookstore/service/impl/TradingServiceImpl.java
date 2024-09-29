package com.onlinebookstore.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.onlinebookstore.common.model.entity.Book;
import com.onlinebookstore.common.model.entity.ShoppingCart;
import com.onlinebookstore.common.model.vo.ShoppingCartVO;
import com.onlinebookstore.repository.BookRepository;
import com.onlinebookstore.repository.ShoppingCartRepository;
import com.onlinebookstore.service.TradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @description Centralized management of fund settlement services
 * @author henryzheng
 * @date  2024/09/26
 */
@Service
public class TradingServiceImpl implements TradingService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;


    @Override
    public JSONObject checkout(List<Integer> shoppingCartIds) {
        if (CollectionUtils.isEmpty(shoppingCartIds)) {
            throw new RuntimeException("Invalid parameter");
        }

        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        List<ShoppingCart> invalidShoppingCarts = new ArrayList<>();
        List<Book> boughtGoods = new ArrayList<>();
        double totalQuantity = 0D;
        double totalAmount = 0D;
        // Looping all the goods that need to be settled.
        for (Integer shoppingCartId : shoppingCartIds) {
            // Query the shopping cart addition record to ensure that the shopping cart addition record is valid.
            Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(shoppingCartId);
            if (!shoppingCart.isPresent()) {
                // If the shopping cart addition record is invalid, it will be recorded in the invalid shopping cart settlement list.
                ShoppingCart invalidShoppingCart = new ShoppingCartVO();
                invalidShoppingCart.setId(shoppingCartId);
                invalidShoppingCarts.add(invalidShoppingCart);
                continue;
            }

            // Query goods information to ensure that the goods being settled are legal
            Optional<Book> book = bookRepository.findById(shoppingCart.get().getGoodsId());
            if (!book.isPresent()) {
                // If the goods being settled is illegal, such as expired, or the goods ID is incorrect,
                // it will be recorded in the invalid goods settlement list.
                invalidShoppingCarts.add(shoppingCart.get());
                continue;
            }

            // Save legal shopping cart records and settled goods information
            shoppingCarts.add(shoppingCart.get());
            boughtGoods.add(book.get());

            // Accumulate the quantity and amount of settled goods
            totalQuantity += shoppingCart.get().getGoodsQuantity();
            totalAmount += shoppingCart.get().getGoodsQuantity() * book.get().getPrice();
        }

        // Output the summary information of settled goods
        Map<String, Double> totalMap = new HashMap<>();
        totalMap.put("totalQuantity", totalQuantity);
        totalMap.put("totalAmount", totalAmount);

        // Output legal shopping cart information, illegal shopping cart information, and settled goods details
        JSONObject checkoutResult = new JSONObject();
        checkoutResult.put("Checkout", totalMap);
        checkoutResult.put("ShoppingCarts", shoppingCarts);
        checkoutResult.put("InvalidShoppingCarts", invalidShoppingCarts);
        checkoutResult.put("BoughtGoods", boughtGoods);

        return checkoutResult;
    }

}
