package test;

import com.hit.dao.OrderItemDao;
import com.hit.dao.impl.OrderItemDaoImpl;
import com.hit.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {

        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null, "java 从入门到精通", 1, new BigDecimal(100), new BigDecimal(100), "1234567891"));
        orderItemDao.saveOrderItem(new OrderItem(null, "javaScript 从入门到精通", 2, new BigDecimal(100), new BigDecimal(200), "1234567891"));
        orderItemDao.saveOrderItem(new OrderItem(null, "Netty 入门", 1, new BigDecimal(100), new BigDecimal(100), "1234567891"));

    }

    @Test
    public void queryOrderItemsByOrderId() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();

        List<OrderItem> orderItems = orderItemDao.queryOrderItemsByOrderId("1234567891");
        System.out.println("orderItems = " + orderItems);
    }
}