package com.onlinebookstore.service.impl;

import com.onlinebookstore.common.model.entity.Book;
import com.onlinebookstore.common.model.entity.ShoppingCart;
import com.onlinebookstore.common.model.vo.ShoppingCartVO;
import com.onlinebookstore.repository.BookRepository;
import com.onlinebookstore.repository.ShoppingCartRepository;
import com.onlinebookstore.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @description Centralized management of shopping cart services
 * @author henryzheng
 * @date  2024/09/26
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void add(ShoppingCartVO shoppingCartVO) {
        if (shoppingCartVO == null) {
            throw new RuntimeException("Invalid parameter");
        }

        // If the goods does not exist, user is not allowed to add it to the shopping cart.
        Optional<Book> book = bookRepository.findById(shoppingCartVO.getGoodsId());
        if (!book.isPresent()) {
            throw new RuntimeException("Not found Goods ID");
        }

        List<ShoppingCart> addedGoodsList = shoppingCartRepository.findByGoodsId(shoppingCartVO.getGoodsId());
        // If user add an item to the shopping cart for the first time, add a new shopping cart record directly.
        if (CollectionUtils.isEmpty(addedGoodsList)) {
            shoppingCartVO.setCreateTime(new Date());
            shoppingCartRepository.save(shoppingCartVO.toEntity());
        } else {
            // If the goods has been added to the shopping cart, the total number of goods in the shopping cart will be accumulated.
            ShoppingCart addedGoods = addedGoodsList.get(0);
            addedGoods.setGoodsQuantity(addedGoods.getGoodsQuantity() + shoppingCartVO.getGoodsQuantity());
            addedGoods.setUpdateTime(new Date());
            shoppingCartRepository.updateGoodsQuantityById(
                    addedGoods.getId(),
                    addedGoods.getGoodsQuantity(),
                    addedGoods.getUpdateTime());
        }
    }

    @Override
    public List<ShoppingCartVO> findAll() {
        return ShoppingCartVO.fromEntities(shoppingCartRepository.findAll());
    }

}
