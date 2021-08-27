package com.hit.web;

import com.hit.pojo.Book;
import com.hit.pojo.Page;
import com.hit.service.impl.BookServiceImpl;
import com.hit.utils.WEBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet {
    private BookServiceImpl bookService = new BookServiceImpl();

    /**
     * @Author DELL
     * @Description  处理分页业务
     * @Date 2021/7/27 20:25
     * @Param [request, response]
     * @Return void
     * @Version 1.0
     */
    public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        System.out.println("经过了前台的ClientBookServlet程序");

        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WEBUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WEBUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用 BookService.page(pageNo，pageSize)：Page 对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        page.setUrl("client/bookServlet?action=page");



        //3 保存 Page 对象到 Request 域中
        request.setAttribute("page",page);
        //4 请求转发到 pages/manager/book_manager.jsp 页面
        request.getRequestDispatcher( "/pages/client/index.jsp").forward(request,response);
    }

    public void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        System.out.println("经过了前台的ClientBookServlet程序");

        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WEBUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WEBUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WEBUtils.parseInt(request.getParameter("min"), 0);
        int max = WEBUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);

        //2 调用 BookService.page(pageNo，pageSize)：Page 对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize,min,max);

        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        //如果有最小价格的参数，追加到分页条的地址参数中
        if(request.getParameter("min") != null){
            sb.append("&min=").append(request.getParameter("min"));
        }

        //如果有最大价格的参数，追加到分页条的地址参数中
        if(request.getParameter("max") != null){
            sb.append("&max=").append(request.getParameter("max"));
        }

        page.setUrl(sb.toString());

        //3 保存 Page 对象到 Request 域中
        request.setAttribute("page",page);
        //4 请求转发到 pages/manager/book_manager.jsp 页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }

    public void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("test...");
    }

}
