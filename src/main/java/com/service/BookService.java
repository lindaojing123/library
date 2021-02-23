package com.service;

import com.entity.Book;
import com.entity.Category;
import com.entity.Page;

import java.util.*;

public interface BookService {

    List<Book> getAllBooks();
    Book getBookById(String id);
    Page<Book> queryBookLike(int pageNo, int pageSize,
                             String searchId,String searchName);
    Page<Book> getBookSort(int pageNo, int pageSize,
                             String searchId,String c2id);
    void deleteOne(String id);
    void addBook(Book book);
    void updateBook(Book book);
    List<Book> getRandomBook();
    List<Book> getBookByTime();
    List<Book> getBookBySale();
    List<Book> selectBookByCid(int cid);
}
