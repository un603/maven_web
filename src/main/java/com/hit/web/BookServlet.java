package com.hit.web;

import com.hit.pojo.Book;
import com.hit.pojo.Page;
import com.hit.service.impl.BookServiceImpl;
import com.hit.utils.WEBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Classname BookServlet
 * @Description TODO
 * @Author DELL
 * @Date 2021/7/27 14:21
 */
public class BookServlet extends BaseServlet {

    private BookServiceImpl bookService = new BookServiceImpl();

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = WEBUtils.parseInt(request.getParameter("pageNo"), 0);
        pageNo += 1;

        //1.获取请求的参数，封装成为Book对象
        Book book = WEBUtils.copyParamToBean(request.getParameterMap(), new Book());

        //2.调用BookService.addBook()保存图书
        bookService.addBook(book);

        //3.跳到图书列表页面
        //当用户提交完请求，浏览器会记录下最后一次请求的全部信息。当用户按下功能键 F5，就会发起浏览器记录的最后一次 请求。
//        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo="+ pageNo);
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数
        int i = WEBUtils.parseInt(request.getParameter("id"), 0);

        //2、调用 bookService.deleteBookById();删除图书
        bookService.deleteBookById(i);

        //3、重定向回图书列表管理页面
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" +
                request.getParameter("pageNo"));

    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、获取请求的参数==封装成为 Book 对象
        Book book = WEBUtils.copyParamToBean(request.getParameterMap(),new Book());
        // 2、调用 BookService.updateBook( book );修改图书
        bookService.updateBook(book);
        // 3、重定向回图书列表管理页面
        // 地址：/工程名/manager/bookServlet?action=list
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" +
                request.getParameter("pageNo"));
    }

    public void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 获取请求的参数图书编号
        int id = WEBUtils.parseInt(request.getParameter("id"), 0);
        //2 调用 bookService.queryBookById 查询图书
        Book book = bookService.queryBookById(id);
        //3 保存到图书到 Request 域中
        request.setAttribute("book", book) ;
        // 4 请求转发到。pages/manager/book_edit.jsp 页面
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
    }

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();

        //2.把全部图书保存到request域中
        request.setAttribute("books", books);

        //3.请求转发到/pages/manager/book_manager.jsp
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);

    }

    /**
     * @Author DELL
     * @Description  处理分页业务
     * @Date 2021/7/27 20:25
     * @Param [request, response]
     * @Return void
     * @Version 1.0
     */
    public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WEBUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WEBUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用 BookService.page(pageNo，pageSize)：Page 对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        page.setUrl("manager/bookServlet?action=page");

        //3 保存 Page 对象到 Request 域中
        request.setAttribute("page",page);
        //4 请求转发到 pages/manager/book_manager.jsp 页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }
}
