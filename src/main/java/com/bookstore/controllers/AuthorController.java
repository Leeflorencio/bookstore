package com.bookstore.controllers;

import com.bookstore.dtos.AuthorDto;
import com.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping
    public ResponseEntity<Object> saveAuthor(@RequestBody AuthorDto authorDto){
        return authorService.saveAuthor(authorDto);
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Example Service";
    }
}
