package com.personalproject.blogapi.services.impl;

import com.personalproject.blogapi.exceptions.ResourceNotFoundException;
import com.personalproject.blogapi.models.Category;
import com.personalproject.blogapi.models.Post;
import com.personalproject.blogapi.models.User;
import com.personalproject.blogapi.payloads.PostDto;
import com.personalproject.blogapi.payloads.PostResponse;
import com.personalproject.blogapi.repository.CategoryRepo;
import com.personalproject.blogapi.repository.PostRepo;
import com.personalproject.blogapi.repository.UserRepo;
import com.personalproject.blogapi.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost=this.postRepo.save(post);
        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {

        Sort sort=sortDir.equals("ascending")? Sort.by(sortBy): Sort.by(sortBy).descending();

        Pageable p= PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> pagePosts= this.postRepo.findAll(p);

        List<Post> posts=pagePosts.getContent();
       List<PostDto>postDtos= posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

       PostResponse postResponse=new PostResponse();

       postResponse.setContent(postDtos);
       postResponse.setPageNumber(pagePosts.getNumber());
       postResponse.setPageSize(pagePosts.getSize());
       postResponse.setTotalElements(pagePosts.getTotalElements());
       postResponse.setTotalPages(pagePosts.getTotalPages());
       postResponse.setLastPage(pagePosts.isLast());

       return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","Post ID",postId));

        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category cat=this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));

        List<Post>posts= this.postRepo.findByCategory(cat);
      List<PostDto>postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public PostResponse getPostsByCategoryPaging(Integer categoryId,Integer pageNumber, Integer pageSize){
        Category cat=this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        Pageable p= PageRequest.of(pageNumber,pageSize);
 //       Page<Post> pagePosts=  this.postRepo.findAllByCategory(cat,p);
//        List<Post> posts=pagePosts.getContent();
        List<Post> posts=this.postRepo.findAllByCategory(cat,p);
        List<PostDto>postDtos= posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();

        postResponse.setContent(postDtos);
//        postResponse.setPageNumber(pagePosts.getNumber());
//        postResponse.setPageSize(pagePosts.getSize());
//        postResponse.setTotalElements(pagePosts.getTotalElements());
//        postResponse.setTotalPages(pagePosts.getTotalPages());
//        postResponse.setLastPage(pagePosts.isLast());

        return postResponse;
    }

    @Override
    public PostResponse getPostsByUserPaging(Integer userId, Integer pageNumber, Integer pageSize) {
        User user=this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
        Pageable p= PageRequest.of(pageNumber,pageSize);
        //       Page<Post> pagePosts=  this.postRepo.findAllByCategory(cat,p);
//        List<Post> posts=pagePosts.getContent();
        List<Post> posts=this.postRepo.findAllByUser(user,p);
        List<PostDto>postDtos= posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();

        postResponse.setContent(postDtos);
//        postResponse.setPageNumber(pagePosts.getNumber());
//        postResponse.setPageSize(pagePosts.getSize());
//        postResponse.setTotalElements(pagePosts.getTotalElements());
//        postResponse.setTotalPages(pagePosts.getTotalPages());
//        postResponse.setLastPage(pagePosts.isLast());

        return postResponse;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user=this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));

        List<Post>posts=  this.postRepo.findByUser(user);
        List<PostDto>postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPostsByTitle(String searchKeyWord) {

        List<Post> posts=this.postRepo.searchByTitle("%"+searchKeyWord+"%");
        List<PostDto>postDtoList=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public List<PostDto> searchPostsByContent(String searchKeyWord) {

        List<Post> posts=this.postRepo.searchByContent("%"+searchKeyWord+"%");
        List<PostDto>postDtoList=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtoList;
    }
}
