package com.hit.dao;

import com.hit.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    int saveOrderItem(OrderItem orderItem);

    List<OrderItem> queryOrderItemsByOrderId(String orderId);

}
