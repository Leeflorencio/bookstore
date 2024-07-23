package com.bookstore.controllers;

import com.bookstore.dtos.BookDto;
import com.bookstore.exceptions.ExceptionSaveBook;
import com.bookstore.models.BookModel;
import com.bookstore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookstore/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;

    }

    @PostMapping
    public ResponseEntity<BookModel> saveBook(@RequestBody BookDto bookDto) throws ExceptionSaveBook {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookDto));
    }

    @GetMapping
    public ResponseEntity<List<BookModel>> getAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable UUID id) throws ExceptionSaveBook {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body("Livro deletado com sucesso");
    }
}
