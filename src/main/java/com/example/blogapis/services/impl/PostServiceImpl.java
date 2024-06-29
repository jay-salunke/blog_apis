package com.example.blogapis.services.impl;

import com.example.blogapis.entities.Category;
import com.example.blogapis.entities.Post;
import com.example.blogapis.entities.User;
import com.example.blogapis.exception.ResourceNotFoundException;
import com.example.blogapis.payloads.PostDTO;
import com.example.blogapis.payloads.PostResponse;
import com.example.blogapis.repositories.CategoryRepo;
import com.example.blogapis.repositories.PostRepo;
import com.example.blogapis.repositories.UserRepo;
import com.example.blogapis.services.PostService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
@NoArgsConstructor
public class PostServiceImpl implements PostService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PostRepo postRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {

        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));

        Post newPost = modelMapper.map(postDTO,Post.class);
        newPost.setTitle(postDTO.getTitle());
        newPost.setContentDescription(postDTO.getContentDescription());
        newPost.setCreatedAt(new Date());
        newPost.setUpdatedAt(new Date());
        newPost.setImageName("default.png");
        newPost.setUser(user);
        newPost.setCategory(category);

        //saving user data on DB
        postRepo.save(newPost);


        return modelMapper.map(newPost,PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDto, Integer id) {
       Post post = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("category","id",id));

       post.setTitle(postDto.getTitle());
       post.setContentDescription(postDto.getContentDescription());
       post.setUpdatedAt(new Date());
       post.setCreatedAt(post.getCreatedAt());
       post.setImageName(postDto.getImageName());
       postRepo.save(post);
        return modelMapper.map(post,PostDTO.class);
    }

    @Override
    public void deletePost(Integer id) {
        Post post = postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("category","id",id));
        postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {

        Pageable page = PageRequest.of(
                pageNumber,
                pageSize,
                sortDir.equals("asc")?Sort.by(sortBy).ascending()
                        :Sort.by(sortBy).descending()
        );

        Page<Post> pagePost = postRepo.findAll(page);
        PostResponse postRepsponse = new PostResponse();
        postRepsponse.setContent(pagePost.getContent().stream().map(x->modelMapper.map(x,PostDTO.class)).collect(Collectors.toList()));
        postRepsponse.setPageNumber(pagePost.getNumber());
        postRepsponse.setPageSize(pagePost.getSize());
        postRepsponse.setTotalPages(pagePost.getTotalPages());
        postRepsponse.setTotalElements((int)pagePost.getTotalElements());
        postRepsponse.setLastPage(pagePost.isLast());

        return postRepsponse;
    }

    @Override
    public PostDTO getPostById(Integer id) {
        Post post = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("category","id",id));
        return modelMapper.map(post,PostDTO.class);
    }

    @Override
    public PostResponse getPostsByCategory(Integer id, Integer pageNumber, Integer pageSize) {
        Category category = categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("category","id",id));
        List<Post> posts = postRepo.findByCategory(category);
        Pageable p = PageRequest.of(pageNumber,pageSize);
        Page<Post> pagePost = postRepo.findAll(p);
        PostResponse postResponse = new PostResponse();


        postResponse.setContent(pagePost.getContent().stream().map(x->modelMapper.map(x, PostDTO.class)).collect(Collectors.toList()));
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements((int)pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostResponse getPostByUser(Integer id,Integer pageNumber,Integer pageSize) {
        User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("user","id",id));
        Pageable p = PageRequest.of(pageNumber,pageSize);
        List<Post> posts = postRepo.findByUser(user);
        Page<Post> pagePost = postRepo.findAll(p);
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(pagePost.getContent().stream().map(x->modelMapper.map(x,PostDTO.class)).collect(Collectors.toList()));
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements((int)pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }


    @Override
    public List<PostDTO> searchPostsByKeyword(String keyword) {
        List<Post> posts = postRepo.findByTitleContaining(keyword);
        return posts.stream().map(x->modelMapper.map(x,PostDTO.class)).collect(Collectors.toList());
    }

}
