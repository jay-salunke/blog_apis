package com.example.blogapis.repositories;

import com.example.blogapis.entities.Category;
import com.example.blogapis.entities.Post;
import com.example.blogapis.entities.User;
import com.example.blogapis.payloads.PostDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String keyword);
}
