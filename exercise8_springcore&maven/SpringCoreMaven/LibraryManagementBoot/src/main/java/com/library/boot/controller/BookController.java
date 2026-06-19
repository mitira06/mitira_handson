package com.library.boot.controller;

import com.library.boot.model.Book;
import com.library.boot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // CREATE -- POST /api/books with a JSON body like {"title":"...", "author":"..."}
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // READ ALL -- GET /api/books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // READ ONE -- GET /api/books/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE -- PUT /api/books/{id} with a JSON body
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                    return ResponseEntity.ok(bookRepository.save(book));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE -- DELETE /api/books/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
