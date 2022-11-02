package com.company.Summative2HarrisKayla.repository;

import com.company.Summative2HarrisKayla.model.Author;
import com.company.Summative2HarrisKayla.model.Book;
import com.company.Summative2HarrisKayla.model.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookRepositoryTest
{
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    PublisherRepository publisherRepository;
    @Autowired
    BookRepository bookRepository;

    @Before
    public void setUp() throws Exception
    {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();
    }

    @Test
    public void shouldAddGetDeleteBook()
    {
        // Create Author and Publisher first
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

        Publisher publisher = new Publisher();
        publisher.setName("Super Official Publisher");
        publisher.setStreet("Publishing St");
        publisher.setCity("New York");
        publisher.setState("NY");
        publisher.setPostalCode("77777");
        publisher.setPhone("777.777.7777");
        publisher.setEmail("superofficialpublisher@email.com");
        publisher = publisherRepository.save(publisher);

        // Add, get, delete book
        Book book = new Book();
        book.setIsbn("123-4-56-78910");
        book.setPublishDate(LocalDate.of(2022,10,30));
        book.setTitle("The Best Book Ever");
        book.setPrice(new BigDecimal("39.89"));
        book.setAuthorId(author.getId());
        book.setPublisher_id(publisher.getId());

        book = bookRepository.save(book);

        Optional<Book> testBook = bookRepository.findById(book.getId());
        assertEquals(testBook.get(), book);

        bookRepository.deleteById(book.getId());
        testBook = bookRepository.findById(book.getId());
        assertFalse(testBook.isPresent());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldAddWithRefIntegrityException()
    {
        Book book = new Book();
        book.setIsbn("123-4-56-78910");
        book.setPublishDate(LocalDate.of(2022,10,30));
        book.setTitle("The Best Book Ever");
        book.setPrice(new BigDecimal("39.89"));
        book.setAuthorId(77);
        book.setPublisher_id(96);

        book = bookRepository.save(book);
    }

    @Test
    public void shouldUpdateBook()
    {
        // Create Author and Publisher first
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

        Publisher publisher = new Publisher();
        publisher.setName("Super Official Publisher");
        publisher.setStreet("Publishing St");
        publisher.setCity("New York");
        publisher.setState("NY");
        publisher.setPostalCode("77777");
        publisher.setPhone("777.777.7777");
        publisher.setEmail("superofficialpublisher@email.com");
        publisher = publisherRepository.save(publisher);

        Book book = new Book();
        book.setIsbn("123-4-56-78910");
        book.setPublishDate(LocalDate.of(2022,10,30));
        book.setTitle("The Best Book Ever");
        book.setPrice(new BigDecimal("39.89"));
        book.setAuthorId(author.getId());
        book.setPublisher_id(publisher.getId());

        book = bookRepository.save(book);

        // Update
        book.setPrice(new BigDecimal("39.99"));
        book.setTitle("The Best Book Ever: Revised Edition");

        bookRepository.save(book);

        Optional<Book> testBook = bookRepository.findById(book.getId());
        assertEquals(testBook.get(), book);
    }

    @Test
    public void shouldFindAllBooks()
    {
        // Create Author and Publisher first
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

        Publisher publisher = new Publisher();
        publisher.setName("Super Official Publisher");
        publisher.setStreet("Publishing St");
        publisher.setCity("New York");
        publisher.setState("NY");
        publisher.setPostalCode("77777");
        publisher.setPhone("777.777.7777");
        publisher.setEmail("superofficialpublisher@email.com");
        publisher = publisherRepository.save(publisher);

        Book book = new Book();
        book.setIsbn("123-4-56-78910");
        book.setPublishDate(LocalDate.of(2022,10,30));
        book.setTitle("The Best Book Ever");
        book.setPrice(new BigDecimal("29.99"));
        book.setAuthorId(author.getId());
        book.setPublisher_id(publisher.getId());
        book = bookRepository.save(book);

        Book book2 = new Book();
        book2.setIsbn("123-4-56-78911");
        book2.setPublishDate(LocalDate.of(2024,7,16));
        book2.setTitle("The Better Book");
        book2.setPrice(new BigDecimal("39.89"));
        book2.setAuthorId(author.getId());
        book2.setPublisher_id(publisher.getId());
        book2 = bookRepository.save(book2);

        Book book3 = new Book();
        book3.setIsbn("123-4-56-78912");
        book3.setPublishDate(LocalDate.of(2024,7,16));
        book3.setTitle("A Book by Someone Else");
        book3.setPrice(new BigDecimal("35.00"));
        book3.setAuthorId(author2.getId());
        book3.setPublisher_id(publisher.getId());
        book3 = bookRepository.save(book3);

        List<Book> allBooks = bookRepository.findAll();
        assertEquals(allBooks.size(), 3);
    }

    @Test
    public void shouldGetBookByAuthor()
    {
        // Create Author and Publisher first
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

        Publisher publisher = new Publisher();
        publisher.setName("Super Official Publisher");
        publisher.setStreet("Publishing St");
        publisher.setCity("New York");
        publisher.setState("NY");
        publisher.setPostalCode("77777");
        publisher.setPhone("777.777.7777");
        publisher.setEmail("superofficialpublisher@email.com");
        publisher = publisherRepository.save(publisher);

        Book book = new Book();
        book.setIsbn("123-4-56-78910");
        book.setPublishDate(LocalDate.of(2022,10,30));
        book.setTitle("The Best Book Ever");
        book.setPrice(new BigDecimal("29.99"));
        book.setAuthorId(author.getId());
        book.setPublisher_id(publisher.getId());
        book = bookRepository.save(book);

        Book book2 = new Book();
        book2.setIsbn("123-4-56-78911");
        book2.setPublishDate(LocalDate.of(2024,7,16));
        book2.setTitle("The Better Book");
        book2.setPrice(new BigDecimal("39.89"));
        book2.setAuthorId(author.getId());
        book2.setPublisher_id(publisher.getId());
        book2 = bookRepository.save(book2);

        Book book3 = new Book();
        book3.setIsbn("123-4-56-78912");
        book3.setPublishDate(LocalDate.of(2024,7,16));
        book3.setTitle("A Book by Someone Else");
        book3.setPrice(new BigDecimal("35.00"));
        book3.setAuthorId(author2.getId());
        book3.setPublisher_id(publisher.getId());
        book3 = bookRepository.save(book3);

        List<Book> bookList = bookRepository.findAllBooksByAuthorId(author.getId());

        assertEquals(bookList.size(), 2);
    }
}