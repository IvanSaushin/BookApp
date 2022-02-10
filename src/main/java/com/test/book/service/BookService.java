package com.test.book.service;

import com.test.book.repository.BookDao;
import com.test.book.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    public List<Book> findAll() {
        return bookDao.findAll();
    }

    public int save(Book book) {
        return bookDao.save(book);
    }

    public List<Book> getBookListGroupByAuthor() {
        return bookDao.getAllGroupByAuthor();
    }
}
