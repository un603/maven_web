package com.hit.service.impl;

import com.hit.dao.BookDao;
import com.hit.dao.impl.BookDaoImpl;
import com.hit.pojo.Book;
import com.hit.pojo.Page;
import com.hit.service.BookService;

import java.util.List;

/**
 * @Classname BookServiceImpl
 * @Description TODO
 * @Author DELL
 * @Date 2021/7/27 14:05
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> bookPage = new Page<>();



        // 设置每页显示的数量
        bookPage.setPageSize(pageSize);

        // 求总记录数
        Long pageTotalCount = bookDao.queryForPageTotalCount();
        bookPage.setPageTotalCount(pageTotalCount);

        // 求总页码
        int pageTotal = (int) (pageTotalCount / pageSize);
        if (pageTotalCount % pageSize > 0) { pageTotal+=1; }

        // 设置总页码
        bookPage.setPageTotal(pageTotal);

//        //数据边界的有效检查
//        if(pageNo<1){
//            pageNo=1;
//        }
//        if (pageNo>pageTotal){
//            pageNo=pageTotal;
//        }
        // 设置当前页码
        bookPage.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (bookPage.getPageNo()- 1) * pageSize;

        // 求当前页数据
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);

        // 设置当前页数据
        bookPage.setItems(items);

        return bookPage;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {

        Page<Book> bookPage = new Page<>();


        // 设置每页显示的数量
        bookPage.setPageSize(pageSize);

        // 求总记录数
        Long pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        bookPage.setPageTotalCount(pageTotalCount);

        // 求总页码
        int pageTotal = (int) (pageTotalCount / pageSize);
        if (pageTotalCount % pageSize > 0) { pageTotal+=1; }

        // 设置总页码
        bookPage.setPageTotal(pageTotal);

        // 设置当前页码
        bookPage.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (bookPage.getPageNo()- 1) * pageSize;

        // 求当前页数据
        List<Book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);

        // 设置当前页数据
        bookPage.setItems(items);

        return bookPage;
    }
}
