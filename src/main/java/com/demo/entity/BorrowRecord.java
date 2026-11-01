package com.demo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BorrowRecord {
    private Long id;
    private Long bookId;
    private Long userId;
    private String userName;
    private LocalDateTime borrowTime;
    private LocalDateTime returnTime;
    private String status;
    private Integer overdueDays;
}
