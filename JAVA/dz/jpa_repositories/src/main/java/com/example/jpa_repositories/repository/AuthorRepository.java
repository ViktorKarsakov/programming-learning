package com.example.jpa_repositories.repository;

import com.example.jpa_repositories.entity.Author;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByEmail(String email);
    List<Author> findByAgeGreaterThan (int age);
    List<Author> findByAgeBetween(int start, int end);
    List<Author> findByCountry(String country);
    List<Author> findByFirstNameOrLastName(String firstName, String lastName);
    List<Author> findByCountryOrderByAgeDesc(String country);
    List<Author> findTop3ByCountryOrderByAgeDesc();
}
