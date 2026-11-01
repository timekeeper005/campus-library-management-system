package com.demo.service;

import com.demo.entity.BorrowRecord;
import com.demo.mapper.BookMapper;
import com.demo.mapper.BorrowRecordMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BorrowService {

    private final BookMapper bookMapper;
    private final BorrowRecordMapper borrowRecordMapper;
    private final OperationLogService operationLogService;

    public BorrowService(BookMapper bookMapper,
                         BorrowRecordMapper borrowRecordMapper,
                         OperationLogService operationLogService) {
        this.bookMapper = bookMapper;
        this.borrowRecordMapper = borrowRecordMapper;
        this.operationLogService = operationLogService;
    }

    @Transactional
    public BorrowRecord borrowBook(Long bookId, Long userId, String userName) {
        var book = bookMapper.getById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Book not found");
        }
        if (book.getStock() == null || book.getStock() <= 0) {
            throw new IllegalStateException("Book is out of stock");
        }

        int updated = bookMapper.reduceStock(bookId);
        if (updated == 0) {
            throw new IllegalStateException("Borrow failed: stock is insufficient");
        }

        BorrowRecord record = new BorrowRecord();
        record.setBookId(bookId);
        record.setUserId(userId);
        record.setUserName(userName);
        record.setBorrowTime(LocalDateTime.now());
        record.setStatus("BORROWED");
        record.setOverdueDays(0);

        borrowRecordMapper.insertBorrowRecord(record);

        operationLogService.record(
                userName,
                "BORROW",
                "BOOK",
                bookId,
                "User " + userName + " borrowed title: " + book.getTitle()
        );

        return record;
    }

    @Transactional
    public BorrowRecord returnBook(Long borrowRecordId, String operatorName) {
        BorrowRecord record = borrowRecordMapper.getById(borrowRecordId);
        if (record == null) {
            throw new IllegalArgumentException("Borrow record not found");
        }
        if ("RETURNED".equals(record.getStatus())) {
            throw new IllegalStateException("This record has already been returned");
        }

        LocalDateTime now = LocalDateTime.now();
        borrowRecordMapper.updateReturnStatus(borrowRecordId, now, "RETURNED");
        bookMapper.increaseStock(record.getBookId());

        operationLogService.record(
                operatorName,
                "RETURN",
                "BORROW_RECORD",
                borrowRecordId,
                "Book returned, record id=" + borrowRecordId
        );

        record.setReturnTime(now);
        record.setStatus("RETURNED");
        return record;
    }

    public List<BorrowRecord> getMyBorrowRecords(Long userId) {
        return borrowRecordMapper.findByUserId(userId);
    }
}
