package com.shreyash.blog.services.impl;

import com.shreyash.blog.entities.Comment;
import com.shreyash.blog.entities.Post;
import com.shreyash.blog.entities.User;
import com.shreyash.blog.exceptions.ResourceNotFoundException;
import com.shreyash.blog.payloads.CommentDto;
import com.shreyash.blog.repositories.CommentRepository;
import com.shreyash.blog.repositories.PostRepository;
import com.shreyash.blog.repositories.UserRepository;
import com.shreyash.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    ModelMapper mapper;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId, Long userId) {

        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post Id",postId));
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User ID",userId));

        Comment comment = this.mapper.map(commentDto, Comment.class);

        comment.setPost(post);

        Comment savedComment = this.commentRepository.save(comment);

        return this.mapper.map(savedComment,CommentDto.class);

    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","Comment Id",commentId));
        this.commentRepository.delete(comment);

    }
}
