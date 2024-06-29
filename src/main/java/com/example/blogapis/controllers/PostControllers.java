package com.example.blogapis.controllers;

import com.example.blogapis.payloads.APIResponse;
import com.example.blogapis.payloads.PostDTO;
import com.example.blogapis.payloads.PostResponse;
import com.example.blogapis.services.FileService;
import com.example.blogapis.services.PostService;
import com.example.blogapis.utils.Constants;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostControllers {
    @Autowired
    PostService postService;
    @Autowired
    FileService fileService;

    @Value("images/")
  private String path;

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
                                                      @RequestParam(value = "pageNumber", defaultValue = Constants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                      @RequestParam(value ="pageSize",defaultValue = Constants.PAGE_SIZE,required = false) Integer pageSize){
        return new ResponseEntity<>(postService.getPostByUser(userId,pageNumber,pageSize),HttpStatus.OK);
    }

    //getPostsByCategory
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable Integer categoryId
        ,@RequestParam(value = "pageNumber",defaultValue = Constants.PAGE_NUMBER,required = false) Integer pageNumber,
        @RequestParam(value = "pageSize",defaultValue = Constants.PAGE_SIZE,required = false) Integer pageSize
    ){
        return new ResponseEntity<>(postService.getPostsByCategory(categoryId,pageNumber,pageSize),HttpStatus.OK);
    }

   @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = Constants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value ="pageSize",defaultValue = Constants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue =Constants.SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue =Constants.SORT_DIR,required = false) String sortDir){
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

   @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDTO>> searchPostsByKeyword(@PathVariable String keyword){
        return new ResponseEntity<>(postService.searchPostsByKeyword(keyword),HttpStatus.OK);
   }

   @PostMapping("/posts/image/upload/{postId}")
   public ResponseEntity<PostDTO> uploadImage(
           @RequestParam("image")MultipartFile image,
           @PathVariable Integer postId
           ) throws IOException {
            PostDTO postDTO = postService.getPostById(postId);
           String fileName = fileService.uploadImage(path,image);

           postDTO.setImageName(fileName);
            PostDTO updatePhoto = postService.updatePost(postDTO,postId);

           return new ResponseEntity<>(updatePhoto,HttpStatus.OK);
   }


   @GetMapping(value = "/posts/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imageName,
                              HttpServletResponse response
                              ) throws IOException {

       InputStream resource = fileService.getResource(path,imageName);
       response.setContentType(MediaType.IMAGE_JPEG_VALUE);
       StreamUtils.copy(resource,response.getOutputStream());

    }
}