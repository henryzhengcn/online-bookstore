package com.onlinebookstore.common.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Date;

/**
 * @description Shopping Cart Entity
 * @author henryzheng
 * @date  2024/09/26
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart implements Serializable {

    @Schema(description = "shopping cart id")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "goods id")
    @NonNull
    private Integer goodsId;

    @Schema(description = "goods quantity")
    @NonNull
    private Double goodsQuantity;

    @Schema(description = "create time")
    private Date createTime;

    @Schema(description = "create time")
    private Date updateTime;

}
