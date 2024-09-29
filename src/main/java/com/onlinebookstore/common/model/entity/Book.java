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
 * @description Book Entity
 * @author henryzheng
 * @date  2024/09/26
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

    @Schema(description = "book id")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "book title")
    @NonNull
    private String title;

    @Schema(description = "book author")
    @NonNull
    private String author;

    @Schema(description = "book price")
    @NonNull
    private Double price;

    @Schema(description = "book category")
    @NonNull
    private String category;

    @Schema(description = "create time")
    private Date createTime;

    @Schema(description = "update time")
    private Date updateTime;

}
