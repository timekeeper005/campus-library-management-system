package com.demo.service;

import com.demo.entity.Book;
import com.demo.mapper.BookMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final BookMapper bookMapper;

    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public List<Book> searchBooks(String keyword) {
        return bookMapper.searchBooks(keyword);
    }

    public Book getBookById(Long id) {
        return bookMapper.getById(id);
    }

    @Transactional
    public Book addBook(Book book) {
        if (book.getStatus() == null || book.getStatus().isBlank()) {
            book.setStatus("AVAILABLE");
        }
        if (book.getStock() == null) {
            book.setStock(0);
        }
        bookMapper.insertBook(book);
        return book;
    }

    @Transactional
    public Book updateBook(Long id, Book book) {
        Book old = bookMapper.getById(id);
        if (old == null) {
            throw new IllegalArgumentException("Book not found");
        }
        book.setId(id);
        bookMapper.updateBook(book);
        return book;
    }
}
