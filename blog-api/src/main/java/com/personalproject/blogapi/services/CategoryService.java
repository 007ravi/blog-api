package com.personalproject.blogapi.services;

import com.personalproject.blogapi.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    ///create
    public CategoryDto createCategory(CategoryDto categoryDto);

    //Update
    public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    //delete
    public void deleteCategory(Integer categoryId);

    //get
    public CategoryDto getCategory(Integer categoryId);

    //getAll
    public List<CategoryDto> getCategories();
}
