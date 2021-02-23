package com.web;

import com.entity.Book;
import com.entity.Category;
import com.entity.Page;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BookService;
import com.service.BookServiceImpl;
import com.service.CategoryService;
import com.service.CategoryServiceImpl;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MainAction extends ActionSupport {
    HttpServletRequest request = ServletActionContext.getRequest();
    CategoryService categoryService = new CategoryServiceImpl();
    BookService bookService = new BookServiceImpl();
    public String show(){
        List<Category> categorys = categoryService.getCategoryList();
        request.setAttribute("categorys",categorys);

        List<Book> books = bookService.getRandomBook();
        request.setAttribute("randomBooks",books);

        List<Book> books2 = bookService.getBookByTime();
        request.setAttribute("timeBooks",books2);

        List<Book> book3 = bookService.getBookBySale();
        request.setAttribute("saleBooks",book3);
        return "show";
    }

    public String bookList(){
        String cid = request.getParameter("cid");

        List<Category> categories2 = categoryService.getCategoryListByPid(Integer.parseInt(cid));
        request.setAttribute("categories2",categories2);

        String c2id = request.getParameter("c2id");
        Category categoryParent = categoryService.queryCategoryParent(Integer.parseInt(c2id));
//        List<Book> bookList = bookService.selectBookByCid(Integer.parseInt(c2id));
//        request.setAttribute("bookList",bookList);

        HttpServletRequest request = ServletActionContext.getRequest();
        //1.获取请求参数
        String strCurrentPage = request.getParameter("currentPage");
        String searchId = request.getParameter("searchId");
        String searchName = request.getParameter("c2id");
        Integer currentPage = null;
        if(strCurrentPage==null){
            currentPage = 1;
        }else{
            currentPage = Integer.valueOf(strCurrentPage);
        }
        //2.调用service
        Page<Book> pages = bookService.getBookSort(currentPage,5,searchId,c2id);

        //3.将数据存入request作用域
        request.setAttribute("pages",pages);
        request.setAttribute("searchId",searchId );
        request.setAttribute("c2id",c2id);
        request.setAttribute("cid",cid);
        request.setAttribute("categoryParent",categoryParent);
        request.setAttribute("currentPage",currentPage );
        return "bookList";
    }

    public String bookDetail(){
        String bookId = request.getParameter("bookId");
        Book book = bookService.getBookById(bookId);
        String cateId = request.getParameter("cateId");
        Category category = categoryService.queryCategoryParent(Integer.parseInt(cateId));
        request.setAttribute("category",category);
        request.setAttribute("book",book);
        return "bookDetail";
    }
}
