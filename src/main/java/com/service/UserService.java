package com.service;
import com.entity.User;

import java.util.*;
public interface UserService {

    List<User> getAllUser();
    void updateStatus(int status,int id);
    void register(User user);
    User selectOneById(String id);
    User login(String email,String password);
    void updateUser(String password,String id);
    User getUserByEmail(String email);
}
