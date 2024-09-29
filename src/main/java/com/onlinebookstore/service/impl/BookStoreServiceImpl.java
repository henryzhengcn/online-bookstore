package com.onlinebookstore.service.impl;

import com.onlinebookstore.common.model.vo.BookVO;
import com.onlinebookstore.repository.BookRepository;
import com.onlinebookstore.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description Centralized management of book services
 * @author henryzheng
 * @date  2024/09/26
 */
@Service
public class BookStoreServiceImpl implements BookStoreService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void add(BookVO bookVO) {
        if (bookVO == null) {
            throw new RuntimeException("Invalid parameter");
        }
        // Save goods creation time
        bookVO.setCreateTime(new Date());
        bookRepository.save(bookVO.toEntity());
    }

    @Override
    public List<BookVO> findAll() {
        return BookVO.fromEntities(bookRepository.findAll());
    }

}
