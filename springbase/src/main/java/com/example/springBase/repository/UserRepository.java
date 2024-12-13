package com.example.springBase.repository;

import com.example.springBase.model.User;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository {

    public List<User> findAll() {
        return Arrays.asList(
                new User(1L, "홍길동", "hong@example.com"),
                new User(2L, "김영희", "kim@example.com")
        );
    }
}
