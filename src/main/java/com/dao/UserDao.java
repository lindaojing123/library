package com.dao;
import com.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.*;

public interface UserDao {

    List<User> getAll();
    void register(User user);
    User getUserById(String id);
    User getUserByEmail(String email);
    void updateStatus(@Param("status")int status,@Param("id")int id);
    User login(@Param("email") String email , @Param("password") String password);
    void updateUser(@Param("password") String password,@Param("id") String id);
}
