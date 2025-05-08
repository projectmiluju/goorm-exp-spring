package com.example.practice.controller;

import com.example.practice.domain.Post;
import com.example.practice.repository.PostRepository;
import com.example.practice.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;


    public PostController(PostRepository postRepository, PostService postService) {
        this.postRepository = postRepository;
        this.postService = postService;
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @GetMapping
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable Long id, @RequestBody Post updatedPost) {
        return postRepository.findById(id)
                .map(post -> {
                    post.setTitle(updatedPost.getTitle());
                    post.setContent(updatedPost.getContent());
                    return postRepository.save(post);
                }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        postRepository.deleteById(id);
    }
}