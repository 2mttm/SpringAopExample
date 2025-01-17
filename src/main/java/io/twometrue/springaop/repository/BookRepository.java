package io.twometrue.springaop.repository;

import io.twometrue.springaop.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByTitle(String title);
}
