package com.website.blog.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.blog.daos.commentRepository;
import com.website.blog.daos.postRepository;
import com.website.blog.entities.comment;
import com.website.blog.entities.post;
import com.website.blog.exceptions.ResourceNotFoundException;
import com.website.blog.payloads.commentDto;

@Service
public class commentServiceImpl implements commentService {

    @Autowired
    private postRepository postRepository;

    @Autowired
    private commentRepository commentRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public commentDto createComment(commentDto CommentDto, Integer postId) {
        post Post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "postId", postId));
        comment Comment = this.mapper.map(CommentDto, comment.class);
        Comment.setPost(Post);
       comment savedComment = this.commentRepository.save(Comment);
       return this.mapper.map(savedComment, commentDto.class);
    }


    @Override
    public void deleteComment(Integer commentId) {
        comment Com = this.commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "commentId", commentId));
        this.commentRepository.delete(Com);
    }

}
