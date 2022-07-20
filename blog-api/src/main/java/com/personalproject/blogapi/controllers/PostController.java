package com.personalproject.blogapi.controllers;

import com.personalproject.blogapi.models.Post;
import com.personalproject.blogapi.payloads.ApiResponse;
import com.personalproject.blogapi.payloads.PostDto;
import com.personalproject.blogapi.payloads.PostResponse;
import com.personalproject.blogapi.services.FileService;
import com.personalproject.blogapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto>createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId){
        PostDto createPost=this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>>getPostsByUser(
            @PathVariable Integer userId
    ){
       List<PostDto>posts= this.postService.getPostsByUser(userId);
       return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/paging/posts")
    public ResponseEntity<PostResponse>getPostsByUserPaging(
            @PathVariable Integer userId,
            @RequestParam (value = "pageNumber", defaultValue = "0",required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize
    ){
        PostResponse posts= this.postService.getPostsByUserPaging(userId,pageNumber,pageSize);
        return new ResponseEntity<PostResponse>(posts,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>>getPostsByCategory(
            @PathVariable Integer categoryId
    ){
        List<PostDto>posts= this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/paging/posts")
    public ResponseEntity<PostResponse>getPostsByCategoryPaging(
            @PathVariable Integer categoryId,
            @RequestParam (value = "pageNumber", defaultValue = "0",required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize
    ){
        PostResponse posts= this.postService.getPostsByCategoryPaging(categoryId,pageNumber,pageSize);
        return new ResponseEntity<PostResponse>(posts,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse>getAllPosts(
            @RequestParam (value = "pageNumber", defaultValue = "0",required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "postId",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "ascending",required = false)String sortDir
    ){
        PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto>getPostById(@PathVariable Integer postId){
        PostDto post= this.postService.getPostById(postId);

        return new ResponseEntity<PostDto>(post,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId)
    {
        this.postService.deletePost(postId);
        return new ApiResponse("Post successfully deleted!! ",true);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId)
    {
        PostDto updatePost=this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
    }

    @GetMapping("/posts/searchTItle/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(
            @PathVariable("keyword") String keywords
    ){
        List<PostDto>postDtos=this.postService.searchPostsByTitle(keywords);
        return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
    }

    @GetMapping("/posts/searchContent/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByContent(
            @PathVariable("keyword") String keywords
    ){
        List<PostDto>postDtos=this.postService.searchPostsByContent(keywords);
        return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
    }

    //post image upload
//    @PostMapping("/post/image/upload/{postId}")
//    @Consumes("multipart/form-data")
//    @Produces("application/json")
    @ResponseBody
    @RequestMapping(    produces="application/json",
            consumes="multipart/form-data",
            method=RequestMethod.POST,
            value="/post/image/upload/{postId}")
    public ResponseEntity<PostDto>uploadPostImage(
            @RequestParam("image")MultipartFile image,
            @PathVariable Integer postId
            ) throws IOException {
              PostDto postDto=this.postService.getPostById(postId);
              String fileName=  this.fileService.uploadImage(path,image);

              postDto.setImageName(fileName);
              PostDto updatePost=this.postService.updatePost(postDto,postId);
                return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
            }
}
