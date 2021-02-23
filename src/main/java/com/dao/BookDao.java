package com.dao;

import com.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.*;
public interface BookDao {

    List<Book> getAllBooks();
    Book selectById(String id);
    List<Book> queryBookLike(@Param("pageNo") int pageNo,
                               @Param("pageSize") int pageSize,
                               @Param("searchId") String searchId,
                               @Param("searchName") String searchName);
    int getTotalSize( @Param("searchId") String searchId,
                      @Param("searchName") String searchName);
    int getTotalSizeByCid(@Param("c2id") String c2id);
    void deleteOne(String id);
    void addBook(Book book);
    void updateBook(Book book);
    List<Book> getBookByTime();
    List<Book> getBookBySale();
    List<Book> getListByCid(int cid);

    List<Book> getBookSort(@Param("pageNo") int pageNo,
                             @Param("pageSize") int pageSize,
                             @Param("searchId") String searchId,
                            @Param("c2id")String c2id);
}
