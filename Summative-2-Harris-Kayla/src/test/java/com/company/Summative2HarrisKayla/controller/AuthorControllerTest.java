package com.company.Summative2HarrisKayla.controller;

import com.company.Summative2HarrisKayla.model.Author;
import com.company.Summative2HarrisKayla.repository.AuthorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
public class AuthorControllerTest 
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorRepository repo;

    private ObjectMapper mapper = new ObjectMapper();

    private Author author;
    private String authorJson;
    private List<Author> allAuthors = new ArrayList<>();
    private String allAuthorsJson;

    @Before
    public void setup() throws Exception 
    {
        Author author = new Author();
        author.setId(1);
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setStreet("Random blvd");
        author.setCity("Austin");
        author.setState("TX");
        author.setPostalCode("12345");
        author.setPhone("123.456.7890");
        author.setEmail("johndoe@email.net");

        authorJson = mapper.writeValueAsString(author);

        Author author2 = new Author();
        author.setId(2);
        author.setFirstName("Jane");
        author.setLastName("Dane");
        author.setStreet("Random st");
        author.setCity("Austin");
        author.setState("TX");
        author.setPostalCode("54321");
        author.setPhone("098.765.4321");
        author.setEmail("janedane@email.com");

        allAuthors.add(author);
        allAuthors.add(author2);

        allAuthorsJson = mapper.writeValueAsString(allAuthors);

    }

    @Test
    public void shouldCreateNewAuthorOnPostRequest() throws Exception
    {
        Author inputAuthor = new Author();
        inputAuthor.setFirstName("John");
        inputAuthor.setLastName("Doe");
        inputAuthor.setStreet("Random blvd");
        inputAuthor.setCity("Austin");
        inputAuthor.setState("TX");
        inputAuthor.setPostalCode("12345");
        inputAuthor.setPhone("123.456.7890");
        inputAuthor.setEmail("johndoe@email.net");
        String inputJson = mapper.writeValueAsString(inputAuthor);

        doReturn(author).when(repo).save(inputAuthor);

        mockMvc.perform(
                        post("/authors")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(authorJson));

    }

    @Test
    public void shouldReturnAuthorById() throws Exception
    {
        doReturn(Optional.of(author)).when(repo).findById(1);

        ResultActions result = mockMvc.perform(
                        get("/authors/1"))
                .andExpect(status().isOk())
                .andExpect((content().json(authorJson))
                );
    }

    @Test
    public void shouldBStatusOkForNonExistentAuthorId() throws Exception
    {
        doReturn(Optional.empty()).when(repo).findById(3);

        mockMvc.perform(
                        get("/authors/1234"))
                .andExpect(status().isOk()
                );

    }

    @Test
    public void shouldReturnAllAuthors() throws Exception {
        doReturn(allAuthors).when(repo).findAll();

        mockMvc.perform(
                        get("/authors"))
                .andExpect(status().isOk())
                .andExpect(content().json(allAuthorsJson)
                );
    }

    @Test
    public void shouldUpdateByIdAndReturn200StatusCode() throws Exception
    {
        mockMvc.perform(
                        put("/authors")
                                .content(authorJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteByIdAndReturn200StatusCode() throws Exception {
        mockMvc.perform(delete("/authors/2")).andExpect(status().isOk());
    }
}