package com.bookstore.service;

import com.bookstore.dtos.BookDto;
import com.bookstore.exceptions.ExceptionSaveBook;
import com.bookstore.models.BookModel;
import com.bookstore.models.ReviewModel;
import com.bookstore.repositories.AuthorRepository;
import com.bookstore.repositories.BookRepository;
import com.bookstore.repositories.PublisherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        try{
            BookModel book = null;
            book.setTitle(bookDto.title());
            book.setPublisher(publisherRepository.findById(bookDto.publisherId()).get());
            book.setAuthors(authorRepository.findAllById(bookDto.authorIds()).stream().collect(Collectors.toSet()));
            //stream para fazer a iteração da lista de ids

            ReviewModel reviewModel = new ReviewModel();
            reviewModel.setComment(bookDto.reviewComment());
            reviewModel.setBook(book);
            book.setReview(reviewModel);

            return bookRepository.save(book);
        }catch (NullPointerException e){
            throw new ExceptionSaveBook("Erro ao cadastrar o livro");
        }

    }

    public List<BookModel> getAllBooks(){
        return bookRepository.findAll();
    }

    @Transactional
    public void deleteBook(UUID id){
        bookRepository.deleteById(id);
    }
}
