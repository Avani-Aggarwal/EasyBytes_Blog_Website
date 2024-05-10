package com.website.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.website.blog.payloads.ApiResponse;
import com.website.blog.payloads.userDto;
import com.website.blog.services.userService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class userController {
    @Autowired
    private userService userService;

    // POST -  create user
    @PostMapping("/")
    public ResponseEntity<userDto> createUser(@Valid @RequestBody userDto UserDto){
       userDto creaUserDto =  this.userService.createUser(UserDto);
       return new ResponseEntity<>(creaUserDto, HttpStatus.CREATED);
    }

    // GET - get user
    @GetMapping("/")
  public ResponseEntity<?> getAllUsers() {
    return ResponseEntity.ok(this.userService.getAllUsers()) ;
  }

  @GetMapping("/{userId}")
  public ResponseEntity<userDto> getUserById(@PathVariable Integer userId) {
    return ResponseEntity.ok(this.userService.getUserById(userId)) ;
  }

    // PUT - update user
    @PutMapping("/{userId}")
  public ResponseEntity<userDto> updateUser(@Valid @RequestBody userDto UserDto, @PathVariable Integer userId) {
    userDto updatedUser =  this.userService.updateUser(UserDto, userId);
    return ResponseEntity.ok(updatedUser);
  }

    // DELETE - delete user
    @DeleteMapping("/{userId}")
  public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
    this.userService.deleteUser(userId);
    return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
  }
  
}
