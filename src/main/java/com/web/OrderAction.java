package com.web;

import com.entity.*;
import com.opensymphony.xwork2.ActionSupport;
import com.service.*;
import com.util.UUIDUtil;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class OrderAction extends ActionSupport {

    Address address;
    BookService bookService = new BookServiceImpl();
    AddressService addressService = new AddressServiceImpl();
    OrderService orderService = new OrderServiceImpl();
    public String orderOk(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        User user = (User) request.getSession().getAttribute("user");
        Map<String, CartItem> cart = (Map<String, CartItem>) request.getSession().getAttribute("cart");
        address.setUserId(user.getId());
        List<Address> addresses = addressService.getAddressByUser(user.getId());
        boolean flag = true;
        for(Address addr:addresses){
            if(address.getDetail().equals(addr.getDetail())){
               flag= false;
            }
        }
        if(flag){
            addressService.saveAddress(address);
        }
        Order order = new Order();
        order.setOrderNumber(UUIDUtil.get16UUID());
        order.setOrderTimes(new Date());
        order.setAddrName(address.getDetail());
        order.setAddrUser(address.getName());
        order.setAddrId(address.getId());
        order.setUserId(user.getId());
        order.setTotalPrice((Double) session.getAttribute("totalPrice"));


        orderService.saveOrder(order);
        request.setAttribute("order",order);
        session.removeAttribute("totalPrice");
        session.removeAttribute("leavePrice");

        for(CartItem item:cart.values()){
            OrderItem  orderItem = new OrderItem();
            orderItem.setAmount(item.getTotalPrice());
            orderItem.setBookId(item.getBook().getId());
            orderItem.setNum(item.getNum());
            orderItem.setBookName(item.getBook().getBookName());
            orderItem.setBookSrc(item.getBook().getSrc());
            orderItem.setPrice(item.getBook().getDdPrice());
            orderItem.setOrderId(order.getId());
            orderService.saveOrderItem(orderItem);
            Book book = bookService.getBookById(item.getBook().getId().toString());
            book.setSaleCount(book.getSaleCount()+item.getNum());
            book.setStock(book.getStock()-item.getNum());
            bookService.updateBook(book);
        }
        session.removeAttribute("cart");
        return "orderOk";
    }

    public String show(){
        HttpServletRequest request = ServletActionContext.getRequest();
        List<Order> orderList = orderService.getAllOrder();
        request.setAttribute("orderList",orderList);
        return "show";
    }


    public String detail(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String orderId =  request.getParameter("orderId");
        List<OrderItem> orderItems = orderService.getAllItemByOid(Integer.parseInt(orderId));
        Order order = orderService.getOrderById(Integer.parseInt(orderId));
        request.setAttribute("orderItems",orderItems);
        request.setAttribute("order",order);
        return "detail";
    }

    public String addressForm(){

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null){
            List<Address> addresses = addressService.getAddressByUser(user.getId());
            session.setAttribute("addresses",addresses);
            return "success";
        }else{
            return "fail";
        }
    }

    public String fillAddress(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String id = request.getParameter("addressId");
        Address address = addressService.getAddressById(Integer.parseInt(id));
        request.setAttribute("address",address);
        return "fillAddress";
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
