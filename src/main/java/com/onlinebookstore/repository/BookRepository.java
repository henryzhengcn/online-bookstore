package com.onlinebookstore.repository;

import com.onlinebookstore.common.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description Book Repository
 * @author henryzheng
 * @date  2024/09/26
 */
public interface BookRepository extends JpaRepository<Book, Integer> {

}
