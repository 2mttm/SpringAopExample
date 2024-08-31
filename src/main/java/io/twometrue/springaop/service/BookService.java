package io.twometrue.springaop.service;

import io.twometrue.springaop.entity.Book;
import io.twometrue.springaop.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<List<Book>> getAll() {
        List<Book> books = repository.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public ResponseEntity<Book> getBookByTitle(String title) {
        Book book = repository.findBookByTitle(title).orElseThrow();
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    public ResponseEntity<Book> addBook(Book book) {
        Book newBook = repository.save(book);
        return new ResponseEntity<>(newBook, HttpStatus.OK);
    }
}
