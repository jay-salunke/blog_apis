package com.example.blogapis.repositories;

import com.example.blogapis.entities.Category;
import com.example.blogapis.entities.Post;
import com.example.blogapis.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);


}
