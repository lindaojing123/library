package com.dao;

import com.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminDao {

    Admin login(@Param("username") String username, @Param("password") String password);
}
