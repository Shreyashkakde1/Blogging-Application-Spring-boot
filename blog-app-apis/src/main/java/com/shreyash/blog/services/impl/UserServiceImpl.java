package com.shreyash.blog.services.impl;

import com.shreyash.blog.entities.User;
import com.shreyash.blog.exceptions.ResourceNotFoundException;
import com.shreyash.blog.payloads.UserDto;
import com.shreyash.blog.repositories.UserRepository;
import com.shreyash.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToEntity(userDto);
        User save = userRepository.save(user);
        return this.entityToDto(save);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        BeanUtils.copyProperties(userDto,user);
        User updatedUser = this.userRepository.save(user);
        return entityToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
      User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
      return entityToDto(user);
    }


    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.entityToDto(user)).collect(Collectors.toList());
        return userDtos;
    }



    @Override
    public void deleteUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        this.userRepository.delete(user);
    }

    private User dtoToEntity(UserDto userDto){
        //        BeanUtils.copyProperties(userDto,user);
        return this.modelMapper.map(userDto,User.class);
    }

    private UserDto entityToDto(User user){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }
}
