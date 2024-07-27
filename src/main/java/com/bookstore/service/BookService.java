package com.bookstore.service;

import com.bookstore.dtos.BookDto;
import com.bookstore.exceptions.ExceptionSaveBook;
import com.bookstore.models.BookModel;
import com.bookstore.models.ReviewModel;
import com.bookstore.repositories.AuthorRepository;
import com.bookstore.repositories.BookRepository;
import com.bookstore.repositories.PublisherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Transactional
    public BookModel saveBook(BookDto bookDto) throws ExceptionSaveBook {
        try {
            BookModel book = new BookModel();
            book.setTitle(bookDto.title());
            book.setPublisher(publisherRepository.findById(bookDto.publisherId()).get());
            book.setAuthors(authorRepository.findAllById(bookDto.authorIds()).stream().collect(Collectors.toSet()));
            //stream para fazer a iteração da lista de ids

            ReviewModel reviewModel = new ReviewModel();
            reviewModel.setComment(bookDto.reviewComment());
            reviewModel.setBook(book);
            book.setReview(reviewModel);

            return bookRepository.save(book);
        } catch (NullPointerException e) {
            throw new ExceptionSaveBook("Erro ao cadastrar o livro");
        }

    }

    public List<BookModel> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public ResponseEntity<Object> deleteBook(UUID id) throws ExceptionSaveBook {
        try {
            Optional identificador = bookRepository.findById(id);

            if (!identificador.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não localizado");
            } else {
                bookRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new ExceptionSaveBook(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir o livro ");
    }

    public ResponseEntity<Object> getOneBook(UUID id) {

        try {
            Optional identificador = bookRepository.findById(id);

            if (!identificador.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
            }
            var livro = identificador.get();
            return ResponseEntity.status(HttpStatus.OK).body(livro);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir o livro " + e);
        }
    }
}
