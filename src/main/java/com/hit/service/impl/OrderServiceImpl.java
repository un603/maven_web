package com.hit.service.impl;

import com.hit.dao.BookDao;
import com.hit.dao.OrderDao;
import com.hit.dao.OrderItemDao;
import com.hit.dao.impl.BookDaoImpl;
import com.hit.dao.impl.OrderDaoImpl;
import com.hit.dao.impl.OrderItemDaoImpl;
import com.hit.pojo.*;
import com.hit.service.OrderService;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @Classname OrderServiceImpl
 * @Description TODO
 * @Author DELL
 * @Date 2021/8/2 17:09
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    /**
     * @Author DELL
     * @Description  生成订单
     * @Date 2021/8/2 17:21
     * @Param [cart, userId]
     * @Return java.lang.String
     * @Version 1.0
     */
    @Override
    public String createOrder(Cart cart, Integer userId) {
        // 订单号===唯一性
        String orderId = System.currentTimeMillis() + "" + userId;

        // 创建一个订单对象
        Order order = new Order(orderId, new Date(System.currentTimeMillis()), cart.getTotalPrice(), 0, userId);

        // 保存订单
        orderDao.saveOrder(order);

        // 遍历购物车中每一个商品项转换成为订单项保存到数据库
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            // 获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();

            // 转换为每一个订单项
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(),
                            cartItem.getPrice(), cartItem.getTotalPrice(), orderId);

            // 保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            //更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }

        // 清空购物车
        cart.clear();

        return orderId;
    }

    /**
     * @Author DELL
     * @Description  查询所有订单
     * @Date 2021/8/2 17:21
     * @Param []
     * @Return java.util.List<com.hit.pojo.Order>
     * @Version 1.0
     */
    @Override
    public List<Order> queryOrders() {
        return orderDao.queryOrders();
    }

    /**
     * @Author DELL
     * @Description  修改订单状态
     * @Date 2021/8/2 17:21
     * @Param [orderId, status]
     * @Return int
     * @Version 1.0
     */
    @Override
    public int changeOrderStatus(String orderId, int status) {
        return orderDao.changeOrderStatus(orderId,status);
    }

    /**
     * @Author DELL
     * @Description  根据用户ID查询订单
     * @Date 2021/8/2 17:21
     * @Param [userId]
     * @Return java.util.List<com.hit.pojo.Order>
     * @Version 1.0
     */
    @Override
    public List<Order> queryOrdersByUserId(int userId) {
        return orderDao.queryOrdersByUserId(userId);
    }
}
