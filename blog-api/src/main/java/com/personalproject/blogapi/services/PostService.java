package com.personalproject.blogapi.services;

import com.personalproject.blogapi.models.Post;
import com.personalproject.blogapi.payloads.PostDto;

import java.util.List;

public interface PostService {
    //create

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    //update
    Post updatePost(PostDto postDto,Integer postId);
    
    //delete
    void deletePost(Integer postId);

    //get al posts
    List<Post>getAllPost();

    //get post by id
    Post get(Integer postId);

    //get all post by category
    List<Post> getPostsByCategory(Integer categoryId);

    //get all post by User
    List<Post> getPostsByUser(Integer userId);

    //searchPost
    List<Post>searchPosts(String searchKeyWord);
}
