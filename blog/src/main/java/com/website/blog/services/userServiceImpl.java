package com.website.blog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.blog.entities.user;
import com.website.blog.exceptions.ResourceNotFoundException;
import com.website.blog.payloads.userDto;
import com.website.blog.daos.userRepository;

@Service
public class userServiceImpl implements userService {

    @Autowired 
    private userRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public userDto createUser(userDto userDto) {
        user User = this.dtoTouser(userDto);
        user savedUser = this.userRepository.save(User);
        return this.userToDto(savedUser);
    }

    @Override
    public userDto updateUser(userDto userDto, Integer userId) {
      user User = this.userRepository.findById(userId)
           .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
           User.setName(userDto.getName());
           User.setEmail(userDto.getEmail());
           User.setPassword(userDto.getPassword());
           User.setAbout(userDto.getAbout());
           User.setGender(userDto.getGender());
           user updatedUser = this.userRepository.save(User);
           userDto userDto1 = this.userToDto(updatedUser);
        return userDto1;
    }

    @Override
    public userDto getUserById(Integer userId) {
      user User = this.userRepository.findById(userId)
              .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
      return this.userToDto(User);
    }

    @Override
    public void deleteUser(Integer userId) {
      user User = this.userRepository.findById(userId)
              .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
      this.userRepository.delete(User);
    }

    @Override
  public List<userDto> getAllUsers() {
    List<user> Users = this.userRepository.findAll();
   List<userDto> userDtos =  Users.stream().map(User-> this.userToDto(User)).collect(Collectors.toList());
    return userDtos;
  }

  public user dtoTouser(userDto UserDto){
    user User = this.modelMapper.map(UserDto, user.class);
    return User;
  }

  public userDto userToDto(user User){
    userDto UserDto = this.modelMapper.map(User, userDto.class);
    return UserDto;
  }
}
