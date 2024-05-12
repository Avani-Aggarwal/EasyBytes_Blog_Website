package com.website.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.website.blog.config.constants;
import com.website.blog.payloads.ApiResponse;
import com.website.blog.payloads.postDto;
import com.website.blog.payloads.postResponse;

import jakarta.servlet.http.HttpServletResponse;

import com.website.blog.services.postService;
import com.website.blog.services.fileService;


@RestController
@RequestMapping("/api/")
public class postController {

    @Autowired
    private postService postService;

    @Autowired
    private fileService fileService;

    @Value("${project.image}")
    private String path;

//  CREATE
   @PostMapping("/user/{userId}/category/{categoryId}/posts")
   public ResponseEntity<postDto> createPost(
    @RequestBody postDto PostDto, 
    @PathVariable Integer userId,
     @PathVariable Integer categoryId){
    postDto createPost = this.postService.createPost(PostDto, userId, categoryId);
    return new ResponseEntity<postDto>(createPost, HttpStatus.CREATED);
   }

// UPDATE
@PutMapping("/posts/{postId}")
public ResponseEntity<postDto> updatePost(@RequestBody postDto PostDto, @PathVariable Integer postId){
    postDto updatedPost = this.postService.updatePost(PostDto, postId);
    return new ResponseEntity<postDto>(updatedPost, HttpStatus.OK);
}

// DELETE
@DeleteMapping("/posts/{postId}")
public ApiResponse deletePost(@PathVariable Integer postId){
    this.postService.deletePost(postId);
    return new ApiResponse("Post is successfully deleted", true);
}

//    GET BY CATEGORY
@GetMapping("/category/{categoryId}/posts")
public ResponseEntity<List<postDto>> getPostByCategory(@PathVariable Integer categoryId){
    List<postDto> posts =  this.postService.getPostByCategory(categoryId);
    return new ResponseEntity<List<postDto>>(posts, HttpStatus.OK);
}
    
// GET BY USER
@GetMapping("/user/{userId}/posts")
public ResponseEntity<List<postDto>> getPostByUser(@PathVariable Integer userId){
    List<postDto> posts =  this.postService.getPostByUser(userId);
    return new ResponseEntity<List<postDto>>(posts, HttpStatus.OK);
}

// GET ALL POST
@GetMapping("/posts")
public ResponseEntity<postResponse> getAllPost(
    @RequestParam(value = "pageNumber", defaultValue = constants.PAGE_NUMBER, required = false) Integer pageNumber,
    @RequestParam(value = "pageSize", defaultValue = constants.PAGE_SIZE, required = false) Integer pageSize,
    @RequestParam(value = "sortBy", defaultValue = constants.SORT_BY, required = false) String sortBy
){
    postResponse allPosts = this.postService.getALLPosts(pageNumber, pageSize, sortBy);
    return new ResponseEntity<postResponse>(allPosts, HttpStatus.OK);
}

// GET POST BY ID
@GetMapping("/posts/{postId}")
public ResponseEntity<postDto> getPostById(@PathVariable Integer postId){
    postDto Post = this.postService.getPostById(postId);
    return new ResponseEntity<postDto>(Post, HttpStatus.OK);
}

// SEARCH
@GetMapping("/posts/search/{keywords}")
public ResponseEntity<List<postDto>> searchPostByTitle(@PathVariable String keywords){

    List<postDto> search = this.postService.searchPost(keywords);
    return new ResponseEntity<List<postDto>>(search, HttpStatus.OK);

}
// POST IMAGE UPLOAD
@PostMapping("/post/image/uplaod/{postId}")
public ResponseEntity<postDto> uploadPostImage(
    @RequestParam MultipartFile image,
    @PathVariable Integer postId
    ) throws IOException{

     postDto PostDto =  this.postService.getPostById(postId);

   String fileName =  this.fileService.uploadImage(path, image);
   
   PostDto.setImageName(fileName);
   postDto updatePost = this.postService.updatePost(PostDto, postId);
   
   return new ResponseEntity<postDto>(updatePost, HttpStatus.OK);
}

// METHOD TO SERVE FILES
@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
public void downloadImage(
    @PathVariable("imageName") String imageName,
    HttpServletResponse response
) throws IOException {
    InputStream resource = this.fileService.getResource(path, imageName);
    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
    StreamUtils.copy(resource, response.getOutputStream());
}
}
