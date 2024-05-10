package com.website.blog.services;

import java.util.List;

import com.website.blog.payloads.categoryDto;

public interface categoryService {

    // create
    categoryDto createCategory(categoryDto CateryDto);

    // update
    categoryDto updateCategory(categoryDto CateryDto, Integer categoryId);

    // delete
    void deleteCategory(Integer categoryId);

    // get
    categoryDto getCategory(Integer categoryId);

    // get all
    List<categoryDto> getCategories();
}
