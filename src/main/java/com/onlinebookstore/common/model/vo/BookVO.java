package com.onlinebookstore.common.model.vo;

import com.onlinebookstore.common.model.entity.Book;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description Book Value Object
 * @author henryzheng
 * @date  2024/09/26
 */
public class BookVO extends Book {

    public Book toEntity() {
        Book book = new Book();
        BeanUtils.copyProperties(this, book);
        return book;
    }

    public static BookVO fromEntity(Book entity) {
        BookVO bookVO = new BookVO();
        BeanUtils.copyProperties(entity, bookVO);
        return bookVO;
    }

    public static List<BookVO> fromEntities(List<Book> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return null;
        }
        List<BookVO> bookVOs = new ArrayList<>(entities.size());
        for (Book entity : entities) {
            bookVOs.add(fromEntity(entity));
        }
        return bookVOs;
    }

}
