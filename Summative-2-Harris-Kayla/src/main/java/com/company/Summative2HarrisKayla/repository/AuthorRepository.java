package com.company.Summative2HarrisKayla.repository;

import com.company.Summative2HarrisKayla.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>
{

}
