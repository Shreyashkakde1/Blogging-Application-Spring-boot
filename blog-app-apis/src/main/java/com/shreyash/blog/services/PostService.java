package com.shreyash.blog.services;

import com.shreyash.blog.entities.Post;
import com.shreyash.blog.payloads.PostDto;
import com.shreyash.blog.payloads.PostResponse;
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
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    // Get Post by ID
    public PostDto getPostById(Long postId);

    // Get all post by Category

    public PostResponse getPostByCategory(Long categoryId, Integer pageNumber, Integer pageSize);

    // Get all post by username
    PostResponse getPostsByUser(Long userId,Integer pageNumber, Integer pageSize);

    // get post by keyword
    List<PostDto> searchPosts(String keyword);
}
