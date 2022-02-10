package com.test.book.repository;

import com.test.book.model.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> findAll() {
        List<Book> bookList = jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<Book>(Book.class));
        return bookList;
    }

    public int save(Book book) {
        System.out.println(book.toString());
        return jdbcTemplate.update("insert into book (id, title,author,description) values (?,?,?,?)",
                book.getId(), book.getTitle(), book.getAuthor(), book.getDescription());
    }

    public List<Book> getAllGroupByAuthor(){
        List<Book> bookList = jdbcTemplate.query("SELECT book.title, book.author, book.description" +
                " FROM public.book" +
                " group by book.title, author, book.description", new BeanPropertyRowMapper<>(Book.class));
        return bookList;
    };
}
