package com.hit.web;

import com.google.gson.Gson;
import com.hit.pojo.Book;
import com.hit.pojo.Cart;
import com.hit.pojo.CartItem;
import com.hit.service.impl.BookServiceImpl;
import com.hit.utils.WEBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {

    private BookServiceImpl bookService = new BookServiceImpl();

    /**
     * @Author DELL
     * @Description  添加商品项
     * @Date 2021/8/2 13:23
     * @Param [request, response]
     * @Return void
     * @Version 1.0
     */
    public void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("添加购物车");
        // 获取请求的参数 商品编号
        int id = WEBUtils.parseInt(request.getParameter("id"), 0);

        // 调用 bookService.queryBookById(id):Book 得到图书的信息
        Book book = bookService.queryBookById(id);

        // 把图书信息，转换成为 CartItem 商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        // 调用 Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }

        cart.addItem(cartItem);

        // 重定向回原来商品所在的地址页面
        //在HTTP协议中有一个请求头，叫Referer，它可以把请求发起时，浏览器地址栏中的地址发送给服务器
        response.sendRedirect(request.getHeader("Referer"));

        //最后一个添加的商品名称
        request.getSession().setAttribute("lastName",cartItem.getName());
    }

    /**
     * @Author DELL
     * @Description  删除商品项
     * @Date 2021/8/2 13:23
     * @Param [request, response]
     * @Return void
     * @Version 1.0
     */
    public void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 获取请求的参数 商品编号
        int id = WEBUtils.parseInt(request.getParameter("id"), 0);

        // 调用 Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart != null) {
            cart.deleteItem(id);
        }

        // 重定向回原来商品所在的地址页面
        //在HTTP协议中有一个请求头，叫Referer，它可以把请求发起时，浏览器地址栏中的地址发送给服务器
        response.sendRedirect(request.getHeader("Referer"));
    }

    /**
     * @Author DELL
     * @Description  清空购物车
     * @Date 2021/8/2 13:23
     * @Param [request, response]
     * @Return void
     * @Version 1.0
     */
    public void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart != null) {
            cart.clear();
        }

        // 重定向回原来商品所在的地址页面
        //在HTTP协议中有一个请求头，叫Referer，它可以把请求发起时，浏览器地址栏中的地址发送给服务器
        response.sendRedirect(request.getHeader("Referer"));
    }

    /**
     * @Author DELL
     * @Description  修改商品数量
     * @Date 2021/8/2 13:40
     * @Param [request, response]
     * @Return void
     * @Version 1.0
     */
    public void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 获取请求的参数 商品编号
        int id = WEBUtils.parseInt(request.getParameter("id"), 0);
        int count = WEBUtils.parseInt(request.getParameter("count"), 0);

        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart != null) {
            cart.updateCount(id,count);
        }

        // 重定向回原来商品所在的地址页面
        //在HTTP协议中有一个请求头，叫Referer，它可以把请求发起时，浏览器地址栏中的地址发送给服务器
        response.sendRedirect(request.getHeader("Referer"));

    }

    public void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("添加购物车");
        // 获取请求的参数 商品编号
        int id = WEBUtils.parseInt(request.getParameter("id"), 0);

        // 调用 bookService.queryBookById(id):Book 得到图书的信息
        Book book = bookService.queryBookById(id);

        // 把图书信息，转换成为 CartItem 商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        // 调用 Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }

        cart.addItem(cartItem);

        //最后一个添加的商品名称
        request.getSession().setAttribute("lastName",cartItem.getName());

        Map<String,Object> resultMap = new HashMap<>();

        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());

        Gson gson = new Gson();

        response.getWriter().write(gson.toJson(resultMap));
    }

}
