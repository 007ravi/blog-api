package com.personalproject.blogapi.controllers;

import com.personalproject.blogapi.payloads.ApiResponse;
import com.personalproject.blogapi.payloads.CategoryDto;
import com.personalproject.blogapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createdCategory=this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
    }


    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer catId){
        CategoryDto updatedCategory=this.categoryService.updateCategory(categoryDto,catId);
        return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
    }


    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> createCategory(@PathVariable Integer catId){
        this.categoryService.deleteCategory(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted Successfully !!",false), HttpStatus.OK);
    }


    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
        CategoryDto category=this.categoryService.getCategory(catId);
        return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);
    }


    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories(){
        List<CategoryDto>list=this.categoryService.getCategories();
        return new ResponseEntity<List<CategoryDto>>(list, HttpStatus.OK);

        //or
        //return ResponseEntity.ok(categories);
    }
}
