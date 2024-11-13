package com.example.practice.service;

import com.example.practice.domain.Post;
import com.example.practice.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Post createPost(Post post) {
        postRepository.save(post);

        // 강제로 예외 발생 → 롤백 테스트
        if (post.getTitle().contains("fail")) {
            throw new RuntimeException("강제 예외 발생: 트랜잭션 롤백 테스트");
        }

        return post;
    }
}