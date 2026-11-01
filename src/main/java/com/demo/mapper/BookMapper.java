package com.demo.mapper;

import com.demo.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {

    List<Book> searchBooks(@Param("keyword") String keyword);

    Book getById(@Param("id") Long id);

    void insertBook(Book book);

    void updateBook(Book book);

    int reduceStock(@Param("bookId") Long bookId);

    int increaseStock(@Param("bookId") Long bookId);
}
