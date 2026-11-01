package com.demo.mapper;

import com.demo.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    SysUser findByUsername(@Param("username") String username);
}
