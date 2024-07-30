package com.bookstore.service;

import com.bookstore.dtos.AuthorDto;
import com.bookstore.models.AuthorModel;
import com.bookstore.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public ResponseEntity<Object> saveAuthor(AuthorDto authorDto) {
        AuthorModel authorModel = new AuthorModel();

        authorModel.setName(authorDto.name());

        authorRepository.save(authorModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Autor salvo com sucesso");
    }
}
