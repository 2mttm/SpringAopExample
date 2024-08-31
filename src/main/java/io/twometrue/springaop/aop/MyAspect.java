package io.twometrue.springaop.aop;

import io.twometrue.springaop.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@Aspect
@Slf4j
public class MyAspect {

    @Around("Pointcuts.allAddMethods()")
    public Object aroundAddingAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Book book = null;
        if (signature.getMethod().equals("addBook")) {
            Object[] args = joinPoint.getArgs();
            for (Object arg : args) {
                if (arg instanceof Book) {
                    book = (Book) arg;
                    log.info("Trying to add a book with title {}", book.getTitle());
                }
            }
        }

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            result = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("Book with title {} has been saved", book.getTitle());
        return result;
    }

    @Around("Pointcuts.allGetMethods()")
    public Object aroundGettingAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String title = null;

        if (methodSignature.getName().equals("getAll")) {
            log.info("Trying to get all the books");
        } else if (methodSignature.getName().equals("getBookByTitle")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof String) {
                    title = (String) arg;
                    log.info("Trying to get book with title {}", title);
                }
            }
        }

        Object result = null;
        try {
            result = joinPoint.proceed();

            if (methodSignature.getName().equals("getAll")) {
                log.info("All the books have been received");
            } else if (methodSignature.getName().equals("getBookByTitle")) {
                log.info("The book with title {} has been received", title);
            }
        } catch (NoSuchElementException e) {
            log.error(e.getMessage(), e);
            result = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            result = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }

}
