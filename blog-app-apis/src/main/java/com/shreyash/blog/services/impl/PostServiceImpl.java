package com.shreyash.blog.services.impl;

import com.shreyash.blog.entities.Category;
import com.shreyash.blog.entities.Post;
import com.shreyash.blog.entities.User;
import com.shreyash.blog.exceptions.ResourceNotFoundException;
import com.shreyash.blog.payloads.PostDto;
import com.shreyash.blog.repositories.CategoryRepository;
import com.shreyash.blog.repositories.PostRepository;
import com.shreyash.blog.repositories.UserRepository;
import com.shreyash.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Post createPost(PostDto postDto,Long userId,Long categoryId) {

        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User Id",userId));
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));


        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        return this.postRepository.save(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(post.getImageName());
        // Save the updated post
        Post save = this.postRepository.save(post);
        // Return the mapped PostDto
        return modelMapper.map(save, PostDto.class);
    }


    @Override
    public void deletePost(Long postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "Post Id", postId)
        );
        this.postRepository.delete(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> all = postRepository.findAll();
        List<PostDto> collect = all.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(
                ()->{
                    return new ResourceNotFoundException("Post","Post Id",postId);
                }
        );
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        List<Post> byCategory = this.postRepository.findByCategory(category);

        List<PostDto> postDtos = byCategory.stream()
                .map(post -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

        List<Post> byUser = this.postRepository.findByUser(user);

        List<PostDto> postDtos = byUser.stream()
                .map(post -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        return postDtos;
    }


    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }
}
