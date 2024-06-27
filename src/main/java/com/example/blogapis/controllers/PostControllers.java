package com.example.blogapis.controllers;

import com.example.blogapis.payloads.APIResponse;
import com.example.blogapis.payloads.PostDTO;
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
    public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable Integer userId){
        return new ResponseEntity<>(postService.getPostByUser(userId),HttpStatus.OK);
    }

    //getPostsByCategory
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Integer categoryId){
        return new ResponseEntity<>(postService.getPostsByCategory(categoryId),HttpStatus.OK);
    }

   @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        return new ResponseEntity<>(postService.getAllPost(),HttpStatus.OK);
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
