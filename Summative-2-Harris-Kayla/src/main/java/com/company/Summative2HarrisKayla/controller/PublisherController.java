package com.company.Summative2HarrisKayla.controller;

import com.company.Summative2HarrisKayla.model.Publisher;
import com.company.Summative2HarrisKayla.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PublisherController
{
    @Autowired
    PublisherRepository repo;

    @PostMapping("/publishers")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher addPublisher(@RequestBody Publisher publisher)
    {
        return repo.save(publisher);
    }

    @GetMapping("/publishers")
    public List<Publisher> getPublishers()
    {
        return repo.findAll();
    }

    @GetMapping("/publishers/{id}")
    public Publisher getPublisherById(@PathVariable int id)
    {
        Optional<Publisher> returnVal = repo.findById(id);
        if(returnVal.isPresent())
        {
            return returnVal.get();
        }
        else
        {
            return null;
        }
    }

    @PutMapping("/publishers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePublisher(@RequestBody Publisher publisher)
    {
        repo.save(publisher);
    }

    @DeleteMapping("/publishers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisher(@PathVariable int id)
    {
        repo.deleteById(id);
    }
}
