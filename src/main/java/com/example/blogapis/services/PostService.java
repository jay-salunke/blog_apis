package com.example.blogapis.services;

import com.example.blogapis.entities.Post;
import com.example.blogapis.payloads.PostDTO;
import com.example.blogapis.payloads.PostResponse;

import java.util.List;

public interface PostService {
    //create
    public PostDTO createPost(PostDTO Post, Integer userId, Integer categoryId);

    //update
    public PostDTO updatePost(PostDTO post,Integer id);

    //delete
    public void deletePost(Integer id);

    //getAllPost
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    //getPost
    public PostDTO getPostById(Integer id);

    //get postByCategory
    public PostResponse getPostsByCategory(Integer id,Integer pageNumber,Integer pageSize);

    //get all the post of the user
    public PostResponse getPostByUser(Integer id,Integer pageNumber,Integer pageSize);

    //search posts by keyword
    List<PostDTO> searchPostsByKeyword(String keyword);

}
