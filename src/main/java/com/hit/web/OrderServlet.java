package com.hit.web;

import com.hit.pojo.Cart;
import com.hit.pojo.Order;
import com.hit.pojo.User;
import com.hit.service.OrderService;
import com.hit.service.impl.OrderServiceImpl;
import com.hit.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();
    UserServiceImpl userService = new UserServiceImpl();

    /**
     * @Author DELL
     * @Description 生成订单
     * @Date 2021/8/2 17:30
     * @Param [request, response]
     * @Return void
     * @Version 1.0
     */
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先获取Cart购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        // 获取 userId
        User loginUser = (User) request.getSession().getAttribute("user");

        if (loginUser == null) {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }

        String orderId = orderService.createOrder(cart, loginUser.getId());

        //使用重定向就不能使用request于，要使用session域
        request.getSession().setAttribute("orderId", orderId);

        //重定向防止表单重复提交
        response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");

    }

    /**
     * @Author DELL
     * @Description 查找用户的所有订单
     * @Date 2021/8/2 19:25
     * @Param [request, response]
     * @Return void
     * @Version 1.0
     */
    protected void queryOrdersById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取请求参数
        User user = (User) request.getSession().getAttribute("user");

        List<Order> orders = orderService.queryOrdersByUserId(user.getId());

        request.getSession().setAttribute("orders", orders);

        response.sendRedirect(request.getContextPath() + "/pages/order/order.jsp");

    }

    /**
     * @Author DELL
     * @Description 查询所有订单
     * @Date 2021/8/2 19:25
     * @Param [request, response]
     * @Return void
     * @Version 1.0
     */
    protected void queryOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> allOrders = orderService.queryOrders();

        request.getSession().setAttribute("allOrders", allOrders);

        response.sendRedirect(request.getContextPath() + "/pages/manager/order_manager.jsp");
    }

    /**
     * @Author DELL
     * @Description 发货服务
     * @Date 2021/8/2 19:24
     * @Param [request, response]
     * @Return void
     * @Version 1.0
     */
    protected void sent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String orderId = (String) request.getSession().getAttribute("orderId");
        String orderId = request.getParameter("orderId");

        orderService.changeOrderStatus(orderId, 1);

//        request.getSession().invalidate();

//        List<Order> allOrders = orderService.queryOrders();
//
//        request.getSession().setAttribute("allOrders",allOrders);

        // 重定向回原来商品所在的地址页面
        //在HTTP协议中有一个请求头，叫Referer，它可以把请求发起时，浏览器地址栏中的地址发送给服务器
        response.sendRedirect(request.getContextPath() + "/orderServlet?action=queryOrders");
    }

    /**
     * @Author DELL
     * @Description 签收
     * @Date 2021/8/2 20:01
     * @Param [request, response]
     * @Return void
     * @Version 1.0
     */
    protected void receive(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String orderId = (String) request.getSession().getAttribute("orderId");
        String orderId = request.getParameter("orderId");

        orderService.changeOrderStatus(orderId, 2);
        //orderServlet?action=receive&orderId=${order.orderId}

//        request.getSession().invalidate();

        List<Order> allOrders = orderService.queryOrders();

        request.getSession().setAttribute("allOrders", allOrders);

        // 重定向回原来商品所在的地址页面
        //在HTTP协议中有一个请求头，叫Referer，它可以把请求发起时，浏览器地址栏中的地址发送给服务器
        response.sendRedirect(request.getContextPath() + "/orderServlet?action=queryOrdersById");
    }

}
