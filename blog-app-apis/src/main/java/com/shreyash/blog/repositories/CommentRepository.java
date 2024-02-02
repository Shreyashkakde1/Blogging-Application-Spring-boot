package com.shreyash.blog.repositories;

import com.shreyash.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
