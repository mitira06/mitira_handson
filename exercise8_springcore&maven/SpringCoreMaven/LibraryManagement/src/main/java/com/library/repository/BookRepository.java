package com.library.repository;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    private List<String> books = new ArrayList<>();

    public void addBook(String title) {
        books.add(title);
        System.out.println("Book added to repository: " + title);
    }

    public List<String> findAll() {
        return books;
    }
}
