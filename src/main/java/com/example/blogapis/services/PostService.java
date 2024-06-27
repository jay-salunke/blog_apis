package com.example.blogapis.services;

import com.example.blogapis.entities.Post;
import com.example.blogapis.payloads.PostDTO;

import java.util.List;

public interface PostService {
    //create
    public PostDTO createPost(PostDTO Post, Integer userId, Integer categoryId);

    //update
    public PostDTO updatePost(PostDTO post,Integer id);

    //delete
    public void deletePost(Integer id);

    //getAllPost
    public List<PostDTO> getAllPost();

    //getPost
    public PostDTO getPostById(Integer id);

    //get postByCategory
    public List<PostDTO> getPostsByCategory(Integer id);

    //get all the post of the user
    public List<PostDTO> getPostByUser(Integer id);

    //search posts by keyword
    List<PostDTO> searchPostsByKeyword(String keyword);

}
