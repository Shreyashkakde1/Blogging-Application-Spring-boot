package com.shreyash.blog.controllers;

import com.shreyash.blog.entities.Category;
import com.shreyash.blog.entities.Post;
import com.shreyash.blog.entities.User;
import com.shreyash.blog.exceptions.ResourceNotFoundException;
import com.shreyash.blog.payloads.ApiResponse;
import com.shreyash.blog.payloads.PostDto;
import com.shreyash.blog.repositories.CategoryRepository;
import com.shreyash.blog.repositories.UserRepository;
import com.shreyash.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    ModelMapper modelMapper;

    // Create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Long userId,
                                              @PathVariable Long categoryId){
        Post createPost = this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(this.modelMapper.map(createPost,PostDto.class), HttpStatus.CREATED);

    }

    // Get Post By userId
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Long userId){
        List<PostDto> postsByUser = this.postService.getPostsByUser(userId);
        return new ResponseEntity<>(postsByUser,HttpStatus.OK);
    }

    //Get Post By Category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Long categoryId){
        List<PostDto> postByCategory = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(postByCategory,HttpStatus.OK);
    }

    // Get All Post
    @GetMapping("/getAllPost")
    public ResponseEntity<List<PostDto>> getAllPost(){
        List<PostDto> allPosts = this.postService.getAllPosts();
        return new ResponseEntity<>(allPosts,HttpStatus.OK);

    }

    // Get Post By I'd
    @GetMapping("GetPostById/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId){
        PostDto postById = this.postService.getPostById(postId);
        return new ResponseEntity<>(postById,HttpStatus.OK);
    }

    // Update Post
    @PutMapping("/updatePost/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long postId,PostDto postDto){
        PostDto updatedPost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    // Delete Post
    @DeleteMapping("/deletePost/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Deleted Successfully",true),HttpStatus.OK);
    }




}
