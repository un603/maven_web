package com.hit.dao.impl;

import com.hit.dao.OrderItemDao;
import com.hit.pojo.OrderItem;

import java.util.List;

/**
 * @Classname OrderItemImpl
 * @Description TODO
 * @Author DELL
 * @Date 2021/8/2 16:24
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql, orderItem.getName(),
                orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());

    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {

        String sql = "select `id`,`name`,`count`,`price`,`total_price` totalPrice,`order_id` orderId from t_order_item " +
                "where `order_id`=?";
        return queryForList(OrderItem.class, sql, orderId);

    }
}
