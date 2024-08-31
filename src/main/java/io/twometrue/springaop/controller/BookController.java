package io.twometrue.springaop.controller;

import io.twometrue.springaop.entity.Book;
import io.twometrue.springaop.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAll() {
        return service.getAll();
    }
    @GetMapping("/books/{title}")
    public ResponseEntity<Book> getBookByTitle(@PathVariable("title") String title) {
        return service.getBookByTitle(title);
    }
@PostMapping("/books")
    public ResponseEntity<Book> addBook(Book book) {
        return service.addBook(book);
    }
}
