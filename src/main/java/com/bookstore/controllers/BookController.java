package com.bookstore.controllers;

import com.bookstore.dtos.BookDto;
import com.bookstore.exceptions.ExceptionSaveBook;
import com.bookstore.models.BookModel;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    @Value("${app.name}")
    private String appName;

    @Value("${app.port}")
    private String appPort;

    public BookController(BookService bookService) {
        this.bookService = bookService;

    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Example Service";
    }

    @PostMapping
    public ResponseEntity<BookModel> saveBook(@RequestBody BookDto bookDto) throws ExceptionSaveBook {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookDto));
    }

    @GetMapping
    public ResponseEntity<List<BookModel>> getAllBooks() {
        System.out.println("App name: " + appName);
        System.out.println("App port: " + appPort);
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable UUID id) throws ExceptionSaveBook {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body("Livro deletado com sucesso");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneBook(@PathVariable UUID id) {
        return bookService.getOneBook(id);
    }
}
