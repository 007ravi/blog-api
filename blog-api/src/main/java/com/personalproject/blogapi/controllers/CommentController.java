package com.personalproject.blogapi.controllers;

import com.personalproject.blogapi.payloads.ApiResponse;
import com.personalproject.blogapi.payloads.CommentDto;
import com.personalproject.blogapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto>createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){
CommentDto createComment=this.commentService.createComment(commentDto,postId);
return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);

    }


    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse>deleteComment(@PathVariable Integer commentId){
       this.commentService.deleteComment(commentId);
       ApiResponse apiResponse=new ApiResponse("Comment Deleted!!! ",Boolean.TRUE);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);

    }
}
