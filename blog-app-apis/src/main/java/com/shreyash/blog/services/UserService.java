package com.shreyash.blog.services;

import com.shreyash.blog.payloads.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public UserDto createUser(UserDto userDto);
    public UserDto updateUser(UserDto userDto,Long userId);
    public UserDto getUserById(Long userId);
    public List<UserDto> getAllUsers();
    public void deleteUserById(Long userId);
}
