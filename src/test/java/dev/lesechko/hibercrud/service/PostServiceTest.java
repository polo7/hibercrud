package dev.lesechko.hibercrud.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import dev.lesechko.hibercrud.repository.PostRepository;
import dev.lesechko.hibercrud.repository.hibernate.HibernatePostRepositoryImpl;
import dev.lesechko.hibercrud.model.Status;
import dev.lesechko.hibercrud.model.Post;


public class PostServiceTest {
    private static Post correctPost = null;
    PostRepository postRepository = Mockito.mock(HibernatePostRepositoryImpl.class);
    PostService postServiceUnderTest = new PostService(postRepository);

    @BeforeAll
    static void init() {
        correctPost = new Post();
        correctPost.setId(1L);
        correctPost.setTitle("Correct Post");
        correctPost.setContent("Content");
        correctPost.setStatus(Status.ACTIVE);
    }

    @Test
    void shouldSaveTest() {
        when(postRepository.save(any())).thenReturn(correctPost);
        assertEquals(correctPost, postServiceUnderTest.save(correctPost));
    }

    @Test
    void shouldGetByCorrectIdTest() {
        when(postRepository.getById(1L)).thenReturn(correctPost);
        assertEquals(correctPost, postRepository.getById(1L));
    }
}
