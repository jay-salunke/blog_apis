package com.example.blogapis.controllers;

import com.example.blogapis.payloads.APIResponse;
import com.example.blogapis.payloads.PostDTO;
import com.example.blogapis.payloads.PostResponse;
import com.example.blogapis.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostControllers {
    @Autowired
    PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId)
    {
        return new ResponseEntity<>(postService.createPost(postDto,userId,categoryId), HttpStatus.CREATED);
    }

    //getPostsByUser
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostByUser(@PathVariable Integer userId,
                                                      @RequestParam(value = "pageNumber", defaultValue = "0",required = false) Integer pageNumber,
                                                      @RequestParam(value ="pageSize",defaultValue = "5",required = false) Integer pageSize){
        return new ResponseEntity<>(postService.getPostByUser(userId,pageNumber,pageSize),HttpStatus.OK);
    }

    //getPostsByCategory
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable Integer categoryId
        ,@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
        @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize
    ){
        return new ResponseEntity<>(postService.getPostsByCategory(categoryId,pageNumber,pageSize),HttpStatus.OK);
    }

   @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value ="pageSize",defaultValue = "9",required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue ="id",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir){
        return new ResponseEntity<>(postService.getAllPost(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
   }

   @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){
        return new ResponseEntity<>(postService.getPostById(postId),HttpStatus.OK);
   }

   @DeleteMapping("/posts/{postId}")
    public ResponseEntity<APIResponse>deletePostById(@PathVariable Integer postId){
        postService.deletePost(postId);
        return ResponseEntity.ok(new APIResponse("PostId: "+postId+" has been successfully deleted",true,HttpStatus.OK.toString()));
   }

   @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> updatePostById(@RequestBody PostDTO postDto,@PathVariable Integer postId){
        return new ResponseEntity<>(postService.updatePost(postDto,postId),HttpStatus.OK);
   }

}