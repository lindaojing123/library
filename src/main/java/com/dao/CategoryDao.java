package com.dao;

import com.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.*;

public interface CategoryDao {

    void addFirst(String category);
    List<Category> getCategory1();
    List<Category> getCategory2();
    void addSecond(Category category);
    void delete(String id);
    Integer getTotalSize();
    public List<Category> queryCategoryByPage(@Param("pageNo") int pageNo,
                                              @Param("pageSize") int pageSize);
    List<Category> selectAllList();
    List<Category> selectListByPid(int pid);
    Category queryCategoryParent(int id);
}
