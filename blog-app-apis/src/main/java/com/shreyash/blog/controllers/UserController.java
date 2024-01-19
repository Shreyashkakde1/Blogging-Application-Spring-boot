package com.shreyash.blog.controllers;

import com.shreyash.blog.payloads.ApiResponse;
import com.shreyash.blog.payloads.UserDto;
import com.shreyash.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    // Post -> Create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    // Put -> update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable Long userId){
        UserDto updatedUser = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    // DELETE -> delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId){
        userService.deleteUserById(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted",true),HttpStatus.OK);

    }
    // GET  -> get user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId){
       return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}
