package com.dao;

import com.entity.Order;
import com.entity.OrderItem;
import java.util.*;

public interface OrderDao {

    void addOrderItem(OrderItem item);
    void addOrder(Order order);
    List<Order> getAllOrder();
    List<OrderItem> getAllOrderItemByOid(Integer orderId);
    Order selectOrderById(Integer id);
}
