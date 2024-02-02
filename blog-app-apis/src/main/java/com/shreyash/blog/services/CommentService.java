package com.shreyash.blog.services;

import com.shreyash.blog.entities.Comment;
import com.shreyash.blog.payloads.CommentDto;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    CommentDto createComment(CommentDto commentDto,Long postId,Long userId);
    void deleteComment(Long commentId);
}
