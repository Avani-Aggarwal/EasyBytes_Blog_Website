package com.website.blog.services;

import java.util.List;

import com.website.blog.payloads.postDto;
import com.website.blog.payloads.postResponse;

public interface postService {

    // CREATE
    postDto createPost(postDto PostDto, Integer userId, Integer categoryId);

    // UPDATE
    postDto updatePost(postDto PostDto, Integer postId);

    // DELETE
    void deletePost(Integer postId);

    // GET ALL POST
    postResponse getALLPosts(Integer pageNumber, Integer pageSize, String sortBy);

    // GET POST BY ID
    postDto getPostById(Integer postId);

    // GET POST BY CATEGORY
    List<postDto> getPostByCategory(Integer categoryId);

    // GET POST BY USER
    List<postDto> getPostByUser(Integer userId);

     // SEARCH
    List<postDto> searchPost(String keyword);

}
