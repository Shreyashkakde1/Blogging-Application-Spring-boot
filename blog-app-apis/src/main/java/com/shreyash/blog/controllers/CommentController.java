package com.shreyash.blog.controllers;

import com.shreyash.blog.payloads.ApiResponse;
import com.shreyash.blog.payloads.CommentDto;
import com.shreyash.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;


    @PostMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable("userId") Long userId,
            @PathVariable("postId") Long postId,
            @RequestBody CommentDto commentDto
    ){
        CommentDto comment = this.commentService.createComment(commentDto, postId, userId);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{commendId}")
    public ResponseEntity<ApiResponse> deleteComment(
            @PathVariable("commentId") Long commentId
    ){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Deleted Successfully",true),HttpStatus.OK);
    }

}
