package com.service;

import com.dao.AddressDao;
import com.dao.OrderDao;
import com.entity.Address;
import com.entity.Order;
import com.entity.OrderItem;
import com.util.MybatisUtil;

import java.util.List;

public class OrderServiceImpl implements OrderService {


    @Override
    public void saveOrderItem(OrderItem item) {
        try{
            OrderDao mapper = MybatisUtil.getMapper(OrderDao.class);
            mapper.addOrderItem(item);
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
    public void saveOrder(Order order) {
        try{
            OrderDao mapper = MybatisUtil.getMapper(OrderDao.class);
            mapper.addOrder(order);
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
    public List<Order> getAllOrder() {
        try{
            OrderDao mapper = MybatisUtil.getMapper(OrderDao.class);
            List<Order> orders = mapper.getAllOrder();
            return orders;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }

    @Override
    public List<OrderItem> getAllItemByOid(Integer oid) {
        try{
            OrderDao mapper = MybatisUtil.getMapper(OrderDao.class);
            List<OrderItem> orderItems = mapper.getAllOrderItemByOid(oid);
            return orderItems;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }

    @Override
    public Order getOrderById(Integer id) {
        try{
            OrderDao mapper = MybatisUtil.getMapper(OrderDao.class);
            Order order = mapper.selectOrderById(id);
            return order;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }


}
