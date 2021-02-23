package com.service;

import com.dao.CategoryDao;
import com.entity.Category;
import com.entity.Page;
import com.util.MybatisUtil;

import java.util.List;

public class CategoryServiceImpl implements CategoryService{

    @Override
    public void addFirst(String  category) {
        try{
            CategoryDao mapper = MybatisUtil.getMapper(CategoryDao.class);
            mapper.addFirst(category);
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
    public List<Category> getCategory1() {
        try{
            CategoryDao mapper = MybatisUtil.getMapper(CategoryDao.class);
            List<Category> categories = mapper.getCategory1();
            return categories;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }

    @Override
    public List<Category> getCategory2() {
        try{
            CategoryDao mapper = MybatisUtil.getMapper(CategoryDao.class);
            List<Category> categories = mapper.getCategory2();
            return categories;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }

    @Override
    public void addSecond(Category category) {
        try{
            CategoryDao mapper = MybatisUtil.getMapper(CategoryDao.class);
            mapper.addSecond(category);
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
    public void delete(String id) {
        try{
            CategoryDao mapper = MybatisUtil.getMapper(CategoryDao.class);
            mapper.delete(id);
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
    public Page<Category> queryCategoryByPage(int pageNo, int pageSize) {
        CategoryDao mapper =  MybatisUtil.getMapper(CategoryDao.class);
        try{
            List<Category> categories = mapper.queryCategoryByPage(pageSize*(pageNo-1),pageSize);
            Page<Category> categoryPage = new Page<>();
            categoryPage.setItems(categories);
            int totalSize = mapper.getTotalSize();
            Integer totalPage = null;
            if (totalSize % pageSize == 0) {
                totalPage = totalSize / pageSize;
            } else {
                totalPage = totalSize / pageSize + 1;
            }
            categoryPage.setPageTotal(totalPage);
            return categoryPage;
        }catch (RuntimeException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }

    @Override
    public List<Category> getCategoryList() {
        try{
            CategoryDao mapper = MybatisUtil.getMapper(CategoryDao.class);
            List<Category > list = mapper.selectAllList();
            return list;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }
    @Override
    public List<Category> getCategoryListByPid(int parentId) {
        try{
            CategoryDao mapper = MybatisUtil.getMapper(CategoryDao.class);
            List<Category> list = mapper.selectListByPid(parentId);
            return list;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }

    @Override
    public Category queryCategoryParent(int id) {
        try{
            CategoryDao mapper = MybatisUtil.getMapper(CategoryDao.class);
            Category category = mapper.queryCategoryParent(id);
            return category;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }

}
