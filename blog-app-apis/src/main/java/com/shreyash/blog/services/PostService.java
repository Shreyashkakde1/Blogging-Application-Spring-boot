package com.shreyash.blog.services;

import com.shreyash.blog.entities.Post;
import com.shreyash.blog.payloads.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    // create
    public Post createPost(PostDto postDto,Long userId,Long categoryId);



    // Update
    public PostDto updatePost(PostDto postDto,Long postId);

    // Delete
    public void deletePost(Long postId);

    // Get All Post
    public List<PostDto> getAllPosts(Integer pageNumber,Integer pageSize);

    // Get Post by ID
    public PostDto getPostById(Long postId);

    // Get all post by Category

    List<PostDto> getPostByCategory(Long categoryId);

    // Get all post by username
    List<PostDto> getPostsByUser(Long userId);

    // get post by keyword
    List<PostDto> searchPosts(String keyword);
}
