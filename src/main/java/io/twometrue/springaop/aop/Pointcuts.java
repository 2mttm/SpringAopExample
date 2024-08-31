package io.twometrue.springaop.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("execution(* io.twometrue.springaop.service.BookService.get*(..))")
    public void allGetMethods() {}

    @Pointcut("execution(* io.twometrue.springaop.service.BookService.add*(..))")
    public void allAddMethods() {}
}
