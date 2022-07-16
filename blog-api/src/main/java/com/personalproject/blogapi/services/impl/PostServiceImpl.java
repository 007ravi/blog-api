package com.personalproject.blogapi.services.impl;

import com.personalproject.blogapi.exceptions.ResourceNotFoundException;
import com.personalproject.blogapi.models.Category;
import com.personalproject.blogapi.models.Post;
import com.personalproject.blogapi.models.User;
import com.personalproject.blogapi.payloads.PostDto;
import com.personalproject.blogapi.repository.CategoryRepo;
import com.personalproject.blogapi.repository.PostRepo;
import com.personalproject.blogapi.repository.UserRepo;
import com.personalproject.blogapi.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private UserRepo userRepo;


    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {

        User user=this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));

        Category category=this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));

        Post post=this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost=this.postRepo.save(post);
     return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public Post updatePost(PostDto postDto, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<Post> getAllPost() {
        return null;
    }

    @Override
    public Post get(Integer postId) {
        return null;
    }

    @Override
    public List<Post> getPostsByCategory(Integer categoryId) {
        return null;
    }

    @Override
    public List<Post> getPostsByUser(Integer userId) {
        return null;
    }

    @Override
    public List<Post> searchPosts(String searchKeyWord) {
        return null;
    }
}
