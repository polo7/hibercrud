package dev.lesechko.hibercrud.service;

import dev.lesechko.hibercrud.model.Post;
import dev.lesechko.hibercrud.repository.PostRepository;
import dev.lesechko.hibercrud.repository.hibernate.HibernatePostRepositoryImpl;

import java.util.List;


public class PostService {
    private final PostRepository postRepository;

    public PostService() {
        postRepository = new HibernatePostRepositoryImpl();
    }

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post save(Post postToSave) {
        return postRepository.save(postToSave);
    }

    public List<Post> getAll() {
        return postRepository.getAll();
    }

    public Post getById(Long id) {
        return postRepository.getById(id);
    }

    public Post update(Post post) {
        return postRepository.update(post);
    }

    public boolean deleteById(Long id) {
        return postRepository.deleteById(id);
    }
}
