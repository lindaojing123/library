package com.web;

import com.entity.Category;
import com.entity.Page;
import com.service.CategoryService;
import com.service.CategoryServiceImpl;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class CategoryAction {
    private String fname;
    private Category category;
    private String categoryId;

    HttpServletRequest request = ServletActionContext.getRequest();
    CategoryService service = new CategoryServiceImpl();
    public String show(){
        HttpServletRequest request = ServletActionContext.getRequest();
        //1.获取请求参数
        String strCurrentPage = request.getParameter("currentPage");
        Integer currentPage = null;
        if(strCurrentPage==null){
            currentPage = 1;
        }else{
            currentPage = Integer.valueOf(strCurrentPage);
        }
        //2.调用service
        Page<Category> pages = service.queryCategoryByPage(currentPage,5);

        //3.将数据存入request作用域
        request.setAttribute("pages",pages);
        return "show";
    }

    public String addFirst(){
        service.addFirst(fname);
        return "addFirst";
    }

    public String addSecondView(){
        List<Category> category1 = service.getCategory1();
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("category1",category1);
        return "addSecondView";
    }

    public String addSecond(){
        service.addSecond(category);
        return "addSecond";
    }

    public String delete(){
        service.delete(categoryId);
        return "delete";
    }



    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
