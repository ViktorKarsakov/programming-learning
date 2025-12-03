package com.example.jpa_repositories.repository;

import com.example.jpa_repositories.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByTitle(String title);
    List<Book> findByPriceGreaterThan (Double price);
    List<Book> findByPublishedYearBetween (int start, int end);
    List<Book> findByAuthorId (Long id);
    List<Book> findByPagesLessThan (Integer pages);
    List<Book> findByTitleContaining (String title);
}
