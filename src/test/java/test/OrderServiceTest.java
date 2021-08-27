package test;

import com.hit.pojo.Cart;
import com.hit.pojo.CartItem;
import com.hit.pojo.Order;
import com.hit.service.OrderService;
import com.hit.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100), new BigDecimal(100)));
        OrderService orderService = new OrderServiceImpl();
        System.out.println("订单号是：" + orderService.createOrder(cart, 1));
    }

    @Test
    public void queryOrders() {
        OrderService orderService = new OrderServiceImpl();
        List<Order> orders =
                orderService.queryOrders();
        System.out.println("orders = " + orders);
    }

    @Test
    public void changeOrderStatus() {
        OrderService orderService = new OrderServiceImpl();
        orderService.changeOrderStatus("16278959045181",2);
    }

    @Test
    public void queryOrdersByUserId() {
        OrderService orderService = new OrderServiceImpl();
        List<Order> orders = orderService.queryOrdersByUserId(1);
        System.out.println("orders = " + orders);
    }
}