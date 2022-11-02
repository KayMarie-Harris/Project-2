package com.company.Summative2HarrisKayla.controller;

import com.company.Summative2HarrisKayla.model.Book;
import com.company.Summative2HarrisKayla.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController
{
    @Autowired
    BookRepository repo;

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book)
    {
        return repo.save(book);
    }

    @GetMapping("/books")
    public List<Book> getBooks()
    {
        return repo.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable int id)
    {
        Optional<Book> returnVal = repo.findById(id);
        if(returnVal.isPresent())
        {
            return returnVal.get();
        }
        else
        {
            return null;
        }
    }

    @PutMapping("/books")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@RequestBody Book book)
    {
        repo.save(book);
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int id)
    {
        repo.deleteById(id);
    }
}
