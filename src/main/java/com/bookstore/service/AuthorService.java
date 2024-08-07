package com.bookstore.service;

import com.bookstore.dtos.AuthorDto;
import com.bookstore.exceptions.ExceptionSaveAuthor;
import com.bookstore.models.AuthorModel;
import com.bookstore.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public ResponseEntity<Object> saveAuthor(AuthorDto authorDto) throws ExceptionSaveAuthor {
        try {
            AuthorModel authorModel = new AuthorModel();
            authorModel.setName(authorDto.name());

            Optional nomeAutor = authorRepository.findByName(authorModel.getName());
            if (nomeAutor.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("O autor(a) " + authorModel.getName() + " já está cadastrado");
            } else {
                authorRepository.save(authorModel);
                return ResponseEntity.status(HttpStatus.CREATED).body("Autor(a) salvo com sucesso");
            }
        } catch (Exception e) {
            throw new ExceptionSaveAuthor("Erro ao cadastrar " + e);
        }

    }

    public ResponseEntity<Object> getOneAuthor(UUID id) {
        try {
            Optional autor = authorRepository.findById(id);

            if (!autor.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Autor(a) não localizado");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(autor);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    public ResponseEntity<Object> getAllAuthors() {
        try {
            List<AuthorModel> listaAutores = authorRepository.findAll();

            if (listaAutores.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum autor(a) encontrado");
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(listaAutores);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e);
        }


    }
}
