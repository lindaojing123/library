package com.service;

import com.dao.BookDao;
import com.dao.CategoryDao;
import com.entity.Book;
import com.entity.Category;
import com.entity.Page;
import com.util.MybatisUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookServiceImpl implements BookService{

    @Override
    public List<Book> getAllBooks() {
        try{
            BookDao mapper = MybatisUtil.getMapper(BookDao.class);
            List<Book> books = mapper.getAllBooks();
            return books;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }

    @Override
    public Book getBookById(String id) {
        try{
            BookDao mapper = MybatisUtil.getMapper(BookDao.class);
            Book book = mapper.selectById(id);
            return book;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }

    @Override
    public Page<Book> queryBookLike(int pageNo, int pageSize, String searchId, String searchName) {
        BookDao mapper =  MybatisUtil.getMapper(BookDao.class);
        try{
            List<Book> books = mapper.queryBookLike(pageSize*(pageNo-1),pageSize,searchId,searchName);
            Page<Book> bookPage = new Page<>();
            bookPage.setItems(books);
            int totalSize = mapper.getTotalSize(searchId,searchName);
            Integer totalPage = null;
            if (totalSize % pageSize == 0) {
                totalPage = totalSize / pageSize;
            } else {
                totalPage = totalSize / pageSize + 1;
            }
            bookPage.setPageTotal(totalPage);
            return bookPage;
        }catch (RuntimeException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }

    @Override
    public Page<Book> getBookSort(int pageNo, int pageSize, String searchId, String c2id) {
        BookDao mapper =  MybatisUtil.getMapper(BookDao.class);
        try{
            List<Book> books = mapper.getBookSort(pageSize*(pageNo-1),pageSize,searchId,c2id);
            Page<Book> bookPage = new Page<>();
            bookPage.setItems(books);
            int totalSize = mapper.getTotalSizeByCid(c2id);
            Integer totalPage = null;
            if (totalSize % pageSize == 0) {
                totalPage = totalSize / pageSize;
            } else {
                totalPage = totalSize / pageSize + 1;
            }
            bookPage.setPageTotal(totalPage);
            return bookPage;
        }catch (RuntimeException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }

    @Override
    public void deleteOne(String id) {
        try{
            BookDao mapper = MybatisUtil.getMapper(BookDao.class);
            mapper.deleteOne(id);
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
    public void addBook(Book book) {
        try{
            BookDao mapper = MybatisUtil.getMapper(BookDao.class);
            mapper.addBook(book);
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
    public void updateBook(Book book) {
        try{
            BookDao mapper = MybatisUtil.getMapper(BookDao.class);
            mapper.updateBook(book);
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
    public List<Book> getRandomBook() {
        try{
            BookDao mapper = MybatisUtil.getMapper(BookDao.class);
            List<Book> books1 = mapper.getAllBooks();
            int index = (int) (Math.random() * books1.size()-1);
            List<Book> books2 = new ArrayList<>();
            books2.add(books1.get(index));
            books2.add(books1.get(index+1));
            return books2;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }

    @Override
    public List<Book> getBookByTime() {
        try{
            BookDao mapper = MybatisUtil.getMapper(BookDao.class);
            List<Book> list = mapper.getBookByTime();
            return list;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }

    @Override
    public List<Book> getBookBySale() {
        try {
            BookDao mapper = MybatisUtil.getMapper(BookDao.class);
            List<Book> books = mapper.getBookBySale();
            return books;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            MybatisUtil.close();
        }
    }

    @Override
    public List<Book> selectBookByCid(int cid) {
        try {
            BookDao mapper = MybatisUtil.getMapper(BookDao.class);
            List<Book> books = mapper.getListByCid(cid);
            return books;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            MybatisUtil.close();
        }
    }
}
