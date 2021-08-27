package com.hit.dao.impl;

import com.hit.dao.BookDao;
import com.hit.pojo.Book;

import java.util.List;

/**
 * @Classname BookDaoImpl
 * @Description TODO
 * @Author DELL
 * @Date 2021/7/27 13:40
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(),
                book.getSales(), book.getStock(),
                book.getImgPath());

    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql, id);

    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id = ?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(),
                book.getSales(), book.getStock(),
                book.getImgPath(), book.getId());

    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , " +
                "`img_path` imgPath from t_book where id = ?";
        return queryForOne(Book.class, sql, id);

    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book";
        return queryForList(Book.class, sql);

    }

    @Override
    public Long queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        return queryForSingleValue(sql);

    }

    @Override
    public Long queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where `price` between ? and ?";
        return queryForSingleValue(sql, min, max);

    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book limit ?,?";
        return queryForList(Book.class, sql, begin, pageSize);


    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath " +
                "from t_book where `price` between ? and ? limit ?,?";
        return queryForList(Book.class, sql, min, max, begin, pageSize);

    }
}
