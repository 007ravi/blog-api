package com.personalproject.blogapi.services;

import com.personalproject.blogapi.models.Post;
import com.personalproject.blogapi.payloads.PostDto;

import java.util.List;

public interface PostService {
    //create

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto,Integer postId);
    
    //delete
    void deletePost(Integer postId);

    //get al posts
    List<PostDto>getAllPost(Integer pageNumber,Integer pageSize);

    //get post by id
    PostDto getPostById(Integer postId);

    //get all post by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    //get all post by User
    List<PostDto> getPostsByUser(Integer userId);

    //searchPost
    List<PostDto>searchPosts(String searchKeyWord);
}
