package com.service;

import com.dao.AdminDao;
import com.dao.BookDao;
import com.entity.Admin;
import com.entity.Book;
import com.entity.User;
import com.util.MybatisUtil;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    @Override
    public Admin login(String username,String password) {
        try{
            AdminDao mapper = MybatisUtil.getMapper(AdminDao.class);
            Admin admin = mapper.login(username,password);
            return admin;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }
}
