package com.website.blog.services;

import com.website.blog.payloads.commentDto;

public interface commentService {

    commentDto createComment(commentDto CommentDto, Integer postId);

    void deleteComment(Integer commentId);
}
