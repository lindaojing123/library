package com.web;

import com.entity.Book;
import com.entity.CartItem;
import com.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BookService;
import com.service.BookServiceImpl;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CartAction extends ActionSupport {

    BookService bookService = new BookServiceImpl();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();
    public String addCart(){
        String bid = request.getParameter("bookId");
        Book book = bookService.getBookById(bid);

        Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");
        CartItem cartItem = new CartItem();
        if(cart==null){
            cart = new HashMap<>();

            cartItem.setBook(book);
            cartItem.setNum(1);
            cartItem.setTotalPrice(book.getDdPrice());
            cartItem.setRealPrice(book.getPrice());
            cart.put(bid,cartItem);
        }else {
            if(!cart.containsKey(bid)){
                cartItem.setBook(book);
                cartItem.setNum(1);
                cartItem.setTotalPrice(book.getDdPrice()+cartItem.getTotalPrice());
                cartItem.setRealPrice(book.getPrice());
                cart.put(bid,cartItem);
            }else {
                cartItem = cart.get(bid);
                cartItem.setNum(cartItem.getNum()+1);
                cartItem.setRealPrice(cartItem.getRealPrice()+cartItem.getBook().getPrice());
                cartItem.setTotalPrice(cartItem.getTotalPrice()+cartItem.getBook().getDdPrice());
            }
        }
        double totalPrice=0;
        double leavePrice=0;
        for(CartItem item : cart.values()){
            totalPrice += item.getTotalPrice();
            leavePrice += (item.getRealPrice()-item.getTotalPrice());
        }
        session.setAttribute("leavePrice",leavePrice);
        session.setAttribute("totalPrice",totalPrice);
        session.setAttribute("cart",cart);
        return "addCart";
    }

    public String changeCart(){
        Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");
        String bid = request.getParameter("bid");
        String number = request.getParameter("number");
        for(String cartKey : cart.keySet()){
            if(cartKey.equals(bid) ){
               CartItem cartItem =  cart.get(bid);
               cartItem.setNum(Integer.parseInt(number));
               cartItem.setRealPrice(cartItem.getNum()*cartItem.getBook().getPrice());
               cartItem.setTotalPrice(cartItem.getNum()*cartItem.getBook().getDdPrice());
            }
        }
        double totalPrice=0;
        double leavePrice=0;
        for(CartItem item : cart.values()){
            totalPrice += item.getTotalPrice();
            leavePrice += item.getRealPrice()-item.getTotalPrice();
        }
        System.out.println("totalPrice"+totalPrice);
        session.setAttribute("leavePrice",leavePrice);
        session.setAttribute("totalPrice",totalPrice);
        session.setAttribute("cart",cart);
        return "changeCart";
    }

    public String deleteCart(){
        String  bookId = request.getParameter("bookId");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Map<String,CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");
        cart.remove(bookId);
        session.setAttribute("cart",cart);
        double totalPrice=0;
        double leavePrice=0;
        for(CartItem item : cart.values()){
            totalPrice += item.getTotalPrice();
            leavePrice += item.getRealPrice()-item.getTotalPrice();
        }
        System.out.println("totalPrice"+totalPrice);
        session.setAttribute("leavePrice",leavePrice);
        session.setAttribute("totalPrice",totalPrice);
        return "deleteCart";

    }

}
