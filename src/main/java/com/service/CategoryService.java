package com.service;

import com.entity.Category;
import com.entity.Page;

import java.util.*;

public interface CategoryService {

    void addFirst(String category);
    List<Category> getCategory1();
    List<Category> getCategory2();
    void addSecond(Category category);
    void delete(String id);
    public Page<Category> queryCategoryByPage(int pageNo, int pageSize);
    public List<Category> getCategoryList();
    List<Category> getCategoryListByPid(int parentId);
    Category queryCategoryParent(int id);
}
