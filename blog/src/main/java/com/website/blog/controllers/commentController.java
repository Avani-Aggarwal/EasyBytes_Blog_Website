package com.website.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.website.blog.payloads.ApiResponse;
import com.website.blog.payloads.commentDto;
import com.website.blog.services.commentService;

@RestController
@RequestMapping("/api/")
public class commentController {

    @Autowired
    private commentService commentService;

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<commentDto> createComment(@RequestBody commentDto CommentDto, @PathVariable Integer postId){
       commentDto createComment =  this.commentService.createComment(CommentDto, postId);
       return new ResponseEntity<commentDto>(createComment, HttpStatus.CREATED); 
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment( @PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully", true), HttpStatus.OK);
    }
}
