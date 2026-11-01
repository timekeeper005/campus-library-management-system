package com.demo.mapper;

import com.demo.entity.BorrowRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface BorrowRecordMapper {
    void insertBorrowRecord(BorrowRecord record);

    BorrowRecord getById(@Param("id") Long id);

    List<BorrowRecord> findByUserId(@Param("userId") Long userId);

    void updateReturnStatus(@Param("id") Long id,
                            @Param("returnTime") LocalDateTime returnTime,
                            @Param("status") String status);
}
