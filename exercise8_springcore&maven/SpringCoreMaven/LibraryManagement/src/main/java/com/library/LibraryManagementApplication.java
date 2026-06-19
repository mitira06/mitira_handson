package com.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.library.service.BookService;

public class LibraryManagementApplication {

    public static void main(String[] args) {
        // Load the Spring container, telling it to read applicationContext.xml from the classpath
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Ask Spring for the bean named "bookService" -- Spring creates it for us
        BookService bookService = (BookService) context.getBean("bookService");

        System.out.println("Spring context loaded successfully!");
        System.out.println("BookService bean: " + bookService);

        // Exercise 2: prove that BookRepository was injected into BookService
        bookService.addBook("Effective Java");
        bookService.addBook("Clean Code");
        System.out.println("Books in repository: " + bookService.getAllBooks());
    }
}
