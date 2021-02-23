package com.test;

import com.entity.Book;
import com.entity.Category;
import com.entity.Order;
import com.entity.Page;
import com.service.*;
import org.junit.Test;

import java.util.*;

public class TestService {

    @Test
    public void test(){
        BookService bookService = new BookServiceImpl();
        Page<Book> pages =  bookService.getBookSort(1,5,"select_price_desc","2");
        System.out.println(pages.getItems());
    }
    @Test
   public void test2(){
       OrderService service = new OrderServiceImpl();
       List<Order> orders = service.getAllOrder();
       System.out.println(orders);
   }

   @Test
    public void test3(){
        BookService service = new BookServiceImpl();
       Page<Book> pages = service.getBookSort(2,5,null,"2");
       System.out.println(pages.getPageTotal());
   }
}
