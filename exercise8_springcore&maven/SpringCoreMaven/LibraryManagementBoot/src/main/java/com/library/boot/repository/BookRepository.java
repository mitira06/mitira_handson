package com.library.boot.repository;

import com.library.boot.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

// Extending JpaRepository<Book, Long> gives us save(), findAll(), findById(),
// deleteById(), and more, for free -- "Long" is the type of Book's id field.
// No method bodies needed; Spring Data generates the implementation at runtime.
public interface BookRepository extends JpaRepository<Book, Long> {
}
