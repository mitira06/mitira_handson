package com.library.service;

import com.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    // BookService NEEDS a BookRepository to do its job.
    // Notice we are NOT writing "new BookRepository()" here ourselves.
    // Spring will create one and hand it to us -- that's Inversion of Control.
    // (No @Autowired needed here anymore -- see the constructor below, which
    // is how Spring actually injects this field as of Exercise 7.)
    private BookRepository bookRepository;

    // Exercise 7: Constructor injection -- dependency is required at object-creation time.
    // When Spring sees a single constructor, it uses it automatically (no @Autowired needed
    // on the constructor itself from Spring 4.3+), passing in the bookRepository bean.
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Exercise 7: Setter injection -- dependency can be supplied (or replaced) after construction.
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(String title) {
        bookRepository.addBook(title);
    }

    public List<String> getAllBooks() {
        return bookRepository.findAll();
    }
}
