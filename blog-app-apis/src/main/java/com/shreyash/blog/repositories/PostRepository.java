package com.shreyash.blog.repositories;

import com.shreyash.blog.entities.Category;
import com.shreyash.blog.entities.Post;
import com.shreyash.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository  extends JpaRepository<Post,Long> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);



}
