package com.personalproject.blogapi.services;

import com.personalproject.blogapi.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId);
    void deleteComment(Integer commentId);
}
