package com.onlinebookstore.repository;

import com.onlinebookstore.common.model.entity.ShoppingCart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @description Shopping Cart Repository
 * @author henryzheng
 * @date  2024/09/26
 */
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    /**
     * Update the quantity of goods added to the shopping cart by the goods ID
     *
     * @param id goods id
     * @param goodsQuantity goods quantity
     * @param updateTime update time
     */
    @Transactional
    @Modifying
    @Query("update ShoppingCart c set c.goodsQuantity = ?2, c.updateTime = ?3 where c.id = ?1")
    public void updateGoodsQuantityById(Integer id, Double goodsQuantity, Date updateTime);

    /**
     * Query the existing addition record of shopping cart based on the goods ID
     *
     * @param goodsId goods id
     * @return The list of existing addition record of shopping cart based on the goods ID
     */
    public List<ShoppingCart> findByGoodsId(Integer goodsId);

}
