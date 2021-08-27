package com.hit.service;

import com.hit.pojo.Cart;
import com.hit.pojo.Order;

import java.util.List;

public interface OrderService {
    String createOrder(Cart cart, Integer userId);

    List<Order> queryOrders();

    int changeOrderStatus(String orderId, int status);

    List<Order> queryOrdersByUserId(int userId);

}
