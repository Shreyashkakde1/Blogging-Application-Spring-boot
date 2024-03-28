package com.shreyash.blog.repositories;

import com.shreyash.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
