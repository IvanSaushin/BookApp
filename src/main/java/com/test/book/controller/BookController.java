package com.test.book.controller;

import com.test.book.model.Book;
import com.test.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    private ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookService.findAll().stream()
                .sorted(Comparator.comparing(Book::getTitle).reversed())
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(books);
    }

    @PostMapping("/create")
    public ResponseEntity<Book> create(@RequestBody Book book) {
        bookService.save(book);
        return ResponseEntity.ok().body(book);
    }

    @GetMapping("/allByAuthor")
    private ResponseEntity<List<Book>> getBooksByAuthor() {
        return ResponseEntity.ok().body(bookService.getBookListGroupByAuthor());
    }
}
