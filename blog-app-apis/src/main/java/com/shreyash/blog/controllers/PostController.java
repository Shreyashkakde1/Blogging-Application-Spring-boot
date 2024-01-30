package com.shreyash.blog.controllers;

import com.shreyash.blog.entities.Post;
import com.shreyash.blog.payloads.ApiResponse;
import com.shreyash.blog.payloads.PostDto;
import com.shreyash.blog.payloads.PostResponse;
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
    public ResponseEntity<PostResponse> getPostByUser(@PathVariable Long userId,
                                                       @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                                       @RequestParam(value = "pageSize",defaultValue ="5",required = false) Integer pageSize){
        PostResponse postsByUser = this.postService.getPostsByUser(userId, pageNumber, pageSize);
        return new ResponseEntity<>(postsByUser,HttpStatus.OK);
    }

    //Get Post By Category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostByCategory(@PathVariable Long categoryId,
                                                           @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                                           @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize){

        PostResponse postByCategory = this.postService.getPostByCategory(categoryId, pageNumber, pageSize);
        return new ResponseEntity<>(postByCategory,HttpStatus.OK);
    }

    // Get All Post
    @GetMapping("/getAllPost")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10", required = false) Integer pageSize
    ){
        PostResponse allPosts = this.postService.getAllPosts(pageNumber, pageSize);
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
