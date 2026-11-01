package com.demo.mapper;

import com.demo.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperationLogMapper {
    void insert(OperationLog log);

    List<OperationLog> listAll();
}
