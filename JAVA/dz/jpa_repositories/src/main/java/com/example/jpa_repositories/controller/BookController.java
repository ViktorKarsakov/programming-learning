package com.example.jpa_repositories.controller;

import com.example.jpa_repositories.entity.Book;
import com.example.jpa_repositories.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/find-by-title")
    public ResponseEntity<?> findByTitle(@RequestParam String title){
        Book book = (Book) bookRepository.findByTitle(title);
        return ResponseEntity.ok().body(book);
    }

    @GetMapping("/find-by-price")
    public ResponseEntity<?> findByPrice(@RequestParam Double price){
        Book book = (Book) bookRepository.findByPriceGreaterThan(price);
        return ResponseEntity.ok().body(book);
    }

    @GetMapping("/find-by-published")
    public ResponseEntity<?> findByPublished(@RequestParam Integer start, @RequestParam Integer end){
        Book book = (Book) bookRepository.findByPublishedYearBetween(start, end);
        return ResponseEntity.ok().body(book);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<?> findById(@RequestParam Long id){
        List<Book> books = bookRepository.findByAuthorId(id);
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/find-by-pages-less")
    public ResponseEntity<?> findByPagesLessThan(@RequestParam Integer pages){
        List<Book> books = bookRepository.findByPagesLessThan(pages);
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/find-by-title-containing")
    public ResponseEntity<?> findByTitleContaining(@RequestParam String title){
        List<Book> books = bookRepository.findByTitleContaining(title);
        return ResponseEntity.ok().body(books);
    }

}
