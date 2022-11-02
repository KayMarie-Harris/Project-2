package com.company.Summative2HarrisKayla.controller;

import com.company.Summative2HarrisKayla.model.Author;
import com.company.Summative2HarrisKayla.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class AuthorController
{
    @Autowired
    AuthorRepository repo;

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Author addAuthor(@RequestBody Author author)
    {
        return repo.save(author);
    }

    @GetMapping("/authors")
    public List<Author> getAuthors()
    {
        return repo.findAll();
    }

    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable int id)
    {
        Optional<Author> returnVal = repo.findById(id);
        if(returnVal.isPresent())
        {
            return returnVal.get();
        }
        else
        {
            return null;
        }
    }

    @PutMapping("/authors")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAuthor(@RequestBody Author author)
    {
        repo.save(author);
    }

    @DeleteMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable int id)
    {
        repo.deleteById(id);
    }
}
