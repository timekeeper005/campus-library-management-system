package com.demo.controller;

import com.demo.entity.BorrowRecord;
import com.demo.mapper.UserMapper;
import com.demo.service.BorrowService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BorrowController {

    private final BorrowService borrowService;
    private final UserMapper userMapper;

    public BorrowController(BorrowService borrowService, UserMapper userMapper) {
        this.borrowService = borrowService;
        this.userMapper = userMapper;
    }

    @PostMapping("/borrow/{bookId}")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public BorrowRecord borrowBook(@PathVariable Long bookId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = userMapper.findByUsername(auth.getName()).getId();
        return borrowService.borrowBook(bookId, userId, auth.getName());
    }

    @PostMapping("/return/{borrowRecordId}")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public BorrowRecord returnBook(@PathVariable Long borrowRecordId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return borrowService.returnBook(borrowRecordId, auth.getName());
    }

    @GetMapping("/borrow/my")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public List<BorrowRecord> myBorrowRecords() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = userMapper.findByUsername(auth.getName()).getId();
        return borrowService.getMyBorrowRecords(userId);
    }
}
