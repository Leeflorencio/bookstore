package com.bookstore.service;

import com.bookstore.dtos.AuthorDto;
import com.bookstore.exceptions.ExceptionSaveAuthor;
import com.bookstore.models.AuthorModel;
import com.bookstore.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public ResponseEntity<Object> saveAuthor(AuthorDto authorDto) throws ExceptionSaveAuthor {
        try {
            AuthorModel authorModel = new AuthorModel();
            authorModel.setName(authorDto.name());

            Optional nomeAutor = authorRepository.findByName(authorModel.getName());
            if (nomeAutor.isPresent()){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("O autor(a) " + authorModel.getName() + " já está cadastrado");
            }else{
                authorRepository.save(authorModel);
                return ResponseEntity.status(HttpStatus.CREATED).body("Autor(a) salvo com sucesso");
            }
        }catch (Exception e){
            throw new ExceptionSaveAuthor("Erro ao cadastrar " + e);
        }

    }
}
