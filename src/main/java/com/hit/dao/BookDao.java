package com.hit.dao;

import com.hit.pojo.Book;

import java.util.List;

public interface BookDao {
    int addBook(Book book);

    int deleteBookById(Integer id);

    int updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryBooks();

    Long queryForPageTotalCount();

    Long queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForPageItems(int begin, int pageSize);


    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);

}
