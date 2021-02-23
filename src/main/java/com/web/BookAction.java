package com.web;

import com.entity.Book;
import com.entity.Category;
import com.entity.Page;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BookService;
import com.service.BookServiceImpl;
import com.service.CategoryService;
import com.service.CategoryServiceImpl;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class BookAction extends ActionSupport {
    private String bookId;
    private String key;
    private String content;
    private Book book;
    File upload;
    // 上传文件的key+FileName : 上传文件的原始文件名
    private String uploadFileName;
    /**
     *  获取上传文件原始类型，是指文件的互联网类型（MIME）类型：
     *  	格式：  父类型/子类型
     *  	比如： text/html
     * 变量名：   上传文件的key+ContentType
     */
    private String uploadContentType;


    CategoryService categoryService = new CategoryServiceImpl();
    BookService service = new BookServiceImpl();
    public String show(){
        HttpServletRequest request = ServletActionContext.getRequest();
        //1.获取请求参数
        String strCurrentPage = request.getParameter("currentPage");
        String searchId = request.getParameter("searchId");
        String searchName = request.getParameter("searchName");
        Integer currentPage = null;
        if(strCurrentPage==null){
            currentPage = 1;
        }else{
            currentPage = Integer.valueOf(strCurrentPage);
        }
        //2.调用service
        Page<Book> pages = service.queryBookLike(currentPage,5,searchId,searchName);

        //3.将数据存入request作用域
        request.setAttribute("pages",pages);
        request.setAttribute("searchId",searchId );
        request.setAttribute("searchName",searchName);
        return "show";
    }

    public String search(){
        HttpServletRequest request = ServletActionContext.getRequest();
        Page<Book> pages = service.queryBookLike(1,5,key,content);
        request.setAttribute("pages",pages);
        request.setAttribute("searchId",key);
        request.setAttribute("searchName",content);
        return "search";
    }

    public String updateView(){
        Book book = service.getBookById(bookId);
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("book",book);
        List<Category> category2 =  categoryService.getCategory2();
        request.setAttribute("category2",category2);
        return "updateView";
    }

    public String addView(){
        HttpServletRequest request = ServletActionContext.getRequest();
        List<Category> category2 =  categoryService.getCategory2();
        request.setAttribute("category2",category2);
        return "addView";
    }

    public String delete(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String id = request.getParameter("bookId");
        service.deleteOne(id);
        return "delete";
    }

    public String addBook(){
        ServletContext servletContext = ServletActionContext.getServletContext();
        //保存上传文件的真实路径
        String realPath = servletContext.getRealPath("upload");
        System.out.println(uploadContentType+" : "+uploadFileName);

        System.out.println("保存上传文件的真实路径："+realPath);
        UUID uuid = UUID.randomUUID();
        // 获取上传文件的后缀
        String extension = FilenameUtils.getExtension(uploadFileName);
        System.out.println("文件的后缀："+extension);
        // 组成新的文件名
        String saveFileName = uuid.toString()+"."+extension;
        System.out.println("组成的新文件名："+saveFileName);
        /**
         * 2.将上传的文件保存到realPath下
         * 	参数一： 上传过来的文件对象
         *  参数二： 目标保存上传文件的 文件对象
         */
        try {
            FileUtils.copyFile(upload, new File(realPath+"/"+saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        book.setSrc(saveFileName);
        service.addBook(book);
        return "addBook";
    }

    public String updateBook(){
        if(uploadFileName == null){
        }else{
            ServletContext servletContext = ServletActionContext.getServletContext();
            //保存上传文件的真实路径
            String realPath = servletContext.getRealPath("upload");
            System.out.println(uploadContentType+" : "+uploadFileName);

            System.out.println("保存上传文件的真实路径："+realPath);
            UUID uuid = UUID.randomUUID();
            // 获取上传文件的后缀
            String extension = FilenameUtils.getExtension(uploadFileName);
            System.out.println("文件的后缀："+extension);
            // 组成新的文件名
            String saveFileName = uuid.toString()+"."+extension;
            System.out.println("组成的新文件名："+saveFileName);
            /**
             * 2.将上传的文件保存到realPath下
             * 	参数一： 上传过来的文件对象
             *  参数二： 目标保存上传文件的 文件对象
             */
            try {
                FileUtils.copyFile(upload, new File(realPath+"/"+saveFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            book.setSrc(saveFileName);
        }
        service.updateBook(book);
        return "updateBook";
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }
}
