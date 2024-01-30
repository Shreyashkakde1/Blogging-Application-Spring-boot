package com.shreyash.blog.services.impl;

import com.shreyash.blog.entities.Category;
import com.shreyash.blog.entities.Post;
import com.shreyash.blog.entities.User;
import com.shreyash.blog.exceptions.ResourceNotFoundException;
import com.shreyash.blog.payloads.PostDto;
import com.shreyash.blog.payloads.PostResponse;
import com.shreyash.blog.repositories.CategoryRepository;
import com.shreyash.blog.repositories.PostRepository;
import com.shreyash.blog.repositories.UserRepository;
import com.shreyash.blog.services.PostService;
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
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy,String sortDir) {

        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }else {
            sort = Sort.by(sortBy).descending();
        }


        Pageable pageable  = PageRequest.of(pageNumber,pageSize, sort);
        Page<Post> pagePost = postRepository.findAll(pageable);

        List<Post> content = pagePost.getContent();


        List<PostDto> collect = content.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(collect);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElement(pagePost.getTotalElements());

        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
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
    public PostResponse getPostByCategory(Long categoryId, Integer pageNumber, Integer pageSize) {
        // Create a Pageable object to represent pagination information
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // Retrieve the Category by ID
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        // Retrieve posts by category with pagination
        Page<Post> pagePost = this.postRepository.findByCategory(category, pageable);

        List<Post> content = pagePost.getContent();

        // Convert Page of Post entities to a Page of PostDto
        List<PostDto> collect = content.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(collect);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElement(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());


        /* Assuming PostResponse has a constructor that takes a Page<PostDto> and other necessary information */
        return postResponse;
    }



    @Override
    public PostResponse getPostsByUser(Long userId, Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber,pageSize);

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

        Page<Post> pagePost = postRepository.findByUser(user,pageable);

        List<Post> content = pagePost.getContent();

        List<PostDto> collect = content.stream()
                .map(post -> this.modelMapper.map(post, PostDto.class))
                .toList();
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(collect);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElement(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());


        return postResponse;
    }


    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }
}
