package com.onlinebookstore.service;

import com.onlinebookstore.common.model.vo.BookVO;

import java.util.List;

/**
 * @description Centralized management of book services
 * @author henryzheng
 * @date  2024/09/26
 */
public interface BookStoreService {

    /**
     * Add a new book
     *
     * @param bookVO The book to be added
     */
    public void add(BookVO bookVO);

    /**
     * Query the list of books added
     *
     * @return List of books added
     */
    public List<BookVO> findAll();

}
