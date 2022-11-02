package com.company.Summative2HarrisKayla.repository;

import com.company.Summative2HarrisKayla.model.Author;
import com.company.Summative2HarrisKayla.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthorRepositoryTest
{
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Before
    public void setUp() throws Exception
    {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    public void shouldAddGetDeleteAuthor()
    {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setStreet("Random blvd");
        author.setCity("Austin");
        author.setState("TX");
        author.setPostalCode("12345");
        author.setPhone("123.456.7890");
        author.setEmail("johndoe@email.net");

        author = authorRepository.save(author);
        Optional<Author> testAuthor = authorRepository.findById(author.getId());
        assertEquals(testAuthor.get(), author);

        authorRepository.deleteById(author.getId());
        testAuthor = authorRepository.findById(author.getId());
        assertFalse(testAuthor.isPresent());

    }

    @Test
    public void shouldUpdateAuthor()
    {
        // Setup
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setStreet("Random blvd");
        author.setCity("Austin");
        author.setState("TX");
        author.setPostalCode("12345");
        author.setPhone("123.456.7890");
        author.setEmail("johndoe@email.net");

        author = authorRepository.save(author);

        // Update
        author.setPhone("098.765.4321");
        author.setEmail("john_doe@email.com");

        authorRepository.save(author);

        Optional<Author> testAuthor = authorRepository.findById(author.getId());
        assertEquals(testAuthor.get(), author);

    }

    @Test
    public void shouldFindAllAuthors()
    {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setStreet("Random blvd");
        author.setCity("Austin");
        author.setState("TX");
        author.setPostalCode("12345");
        author.setPhone("123.456.7890");
        author.setEmail("johndoe@email.net");
        author = authorRepository.save(author);

        Author author2 = new Author();
        author2.setFirstName("Someone");
        author2.setLastName("Else");
        author2.setStreet("Other blvd");
        author2.setCity("Austin");
        author2.setState("TX");
        author2.setPostalCode("12345");
        author2.setPhone("123.444.7777");
        author2.setEmail("someoneelses@email.com");
        author2 = authorRepository.save(author2);

        List<Author> allAuthors = authorRepository.findAll();
        assertEquals(allAuthors.size(),2);
    }
}