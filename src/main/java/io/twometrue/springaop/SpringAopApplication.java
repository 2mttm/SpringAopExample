package io.twometrue.springaop;

import io.twometrue.springaop.entity.Book;
import io.twometrue.springaop.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAopApplication implements CommandLineRunner {
    private final BookRepository repository;

    public SpringAopApplication(BookRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringAopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Book book1 = new Book("Война и мир", "Лев Толстой");
//        Book book2 = new Book("Капитанская дочка", "Александр Пушкин");
//
//        repository.save(book1);
//        repository.save(book2);
    }
}
