package com.service;

import com.entity.Address;
import com.entity.Order;
import com.entity.OrderItem;
import java.util.*;
public interface OrderService {

    void saveOrderItem(OrderItem item);
    void saveOrder(Order order);
    List<Order> getAllOrder();
    List<OrderItem> getAllItemByOid(Integer oid);
    Order getOrderById(Integer id);
}
