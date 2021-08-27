package test;

import com.hit.dao.OrderDao;
import com.hit.dao.impl.OrderDaoImpl;
import com.hit.pojo.Order;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class OrderDaoTest {

    @Test
    public void saveOrder() {

        OrderDao orderDao = new OrderDaoImpl();

        orderDao.saveOrder(new Order("12345678916",new Date(System.currentTimeMillis()),new BigDecimal(100),0, 1));
    }

    @Test
    public void queryOrders() {
        OrderDao orderDao = new OrderDaoImpl();

        List<Order> orders = orderDao.queryOrders();

        System.out.println(orders);
    }

    @Test
    public void changeOrderStatus() {
        OrderDao orderDao = new OrderDaoImpl();

        orderDao.changeOrderStatus("12345678916",2);
    }

    @Test
    public void queryOrdersByUserId() {
        OrderDao orderDao = new OrderDaoImpl();

        List<Order> orders = orderDao.queryOrdersByUserId(1);

        System.out.println(orders);
    }
}