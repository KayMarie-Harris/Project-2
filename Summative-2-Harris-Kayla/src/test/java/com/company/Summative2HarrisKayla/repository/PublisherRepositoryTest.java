package com.company.Summative2HarrisKayla.repository;

import com.company.Summative2HarrisKayla.model.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublisherRepositoryTest
{
    @Autowired
    PublisherRepository publisherRepository;

    @Before
    public void setUp() throws Exception
    {
        publisherRepository.deleteAll();
    }

    @Test
    public void shouldAddGetDeletePublisher()
    {
        Publisher publisher = new Publisher();
        publisher.setName("Super Official Publisher");
        publisher.setStreet("Publishing St");
        publisher.setCity("New York");
        publisher.setState("NY");
        publisher.setPostalCode("77777");
        publisher.setPhone("777.777.7777");
        publisher.setEmail("superofficialpublisher@email.com");

        publisher = publisherRepository.save(publisher);

        Optional<Publisher> testPublisher = publisherRepository.findById(publisher.getId());
        assertEquals(testPublisher.get(),publisher);
    }

    @Test
    public void shouldUpdatePublisher()
    {
        Publisher publisher = new Publisher();
        publisher.setName("Super Official Publisher");
        publisher.setStreet("Publishing St");
        publisher.setCity("New York");
        publisher.setState("NY");
        publisher.setPostalCode("77777");
        publisher.setPhone("777.777.7777");
        publisher.setEmail("superofficialpublisher@email.com");

        publisher = publisherRepository.save(publisher);

        publisher.setName("Super Duper Official Publisher");
        publisher.setEmail("superduperofficalpublisher@email.com");

        publisherRepository.save(publisher);

        Optional<Publisher> testPublisher = publisherRepository.findById(publisher.getId());

    }

    @Test
    public void shouldFindALlPublishers()
    {
        Publisher publisher = new Publisher();
        publisher.setName("Super Official Publisher");
        publisher.setStreet("Publishing St");
        publisher.setCity("New York");
        publisher.setState("NY");
        publisher.setPostalCode("77777");
        publisher.setPhone("777.777.7777");
        publisher.setEmail("superofficialpublisher@email.com");
        publisher = publisherRepository.save(publisher);

        Publisher publisher2 = new Publisher();
        publisher2.setName("Super legit Publisher");
        publisher2.setStreet("Publishing St");
        publisher2.setCity("New York");
        publisher2.setState("NY");
        publisher2.setPostalCode("77777");
        publisher2.setPhone("888.888.8888");
        publisher2.setEmail("superlegitpublisher@email.com");
        publisher2 = publisherRepository.save(publisher2);

        List<Publisher> allPublishers = publisherRepository.findAll();
        assertEquals(allPublishers.size(),2);
    }
}