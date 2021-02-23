package com.service;

import com.dao.BookDao;
import com.dao.UserDao;
import com.entity.Book;
import com.entity.User;
import com.util.MybatisUtil;

import java.util.List;

public class UserServiceImpl implements UserService{
    @Override
    public List<User> getAllUser() {
        try{
            UserDao mapper = MybatisUtil.getMapper(UserDao.class);
            List<User> users = mapper.getAll();
            return users;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }

    @Override
    public void updateStatus(int status, int id) {
        try{
            UserDao mapper = MybatisUtil.getMapper(UserDao.class);
            mapper.updateStatus(status,id);
            MybatisUtil.commit();
        }catch (Exception e){
            e.printStackTrace();
            MybatisUtil.rollback();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }

    @Override
    public void register(User user) {
        try{
            UserDao mapper = MybatisUtil.getMapper(UserDao.class);
            mapper.register(user);
            MybatisUtil.commit();
        }catch (Exception e){
            e.printStackTrace();
            MybatisUtil.rollback();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }

    @Override
    public User selectOneById(String id) {
        try{
            UserDao mapper = MybatisUtil.getMapper(UserDao.class);
            User user = mapper.getUserById(id);
            return user;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }

    @Override
    public User login(String email, String password) {
        try{
            UserDao mapper = MybatisUtil.getMapper(UserDao.class);
            User user = mapper.login(email,password);
            return user;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }

    @Override
    public void updateUser(String passsword,String id) {
        try{
            UserDao mapper = MybatisUtil.getMapper(UserDao.class);
            mapper.updateUser(passsword,id);
            MybatisUtil.commit();
        }catch (Exception e){
            e.printStackTrace();
            MybatisUtil.rollback();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try{
            UserDao mapper = MybatisUtil.getMapper(UserDao.class);
            User user = mapper.getUserByEmail(email);
            return user;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }
}
