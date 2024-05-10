package com.website.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.website.blog.services.categoryService;
import com.website.blog.payloads.ApiResponse;
import com.website.blog.payloads.categoryDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class categoryController {
    @Autowired
    private categoryService categoryService;

    // create
    @PostMapping("/")
    public ResponseEntity<categoryDto> createCategory(@Valid @RequestBody categoryDto CateryDto){
        categoryDto createCategory = this.categoryService.createCategory(CateryDto);
        return new ResponseEntity<categoryDto>(createCategory, HttpStatus.CREATED);
    }

    // update
    @PutMapping("/{categoryId}")
    public ResponseEntity<categoryDto> updateCategory(@Valid @RequestBody categoryDto CateryDto, @PathVariable Integer categoryId){
        categoryDto updateCategory = this.categoryService.updateCategory(CateryDto, categoryId);
        return new ResponseEntity<categoryDto>(updateCategory, HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
    this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);
    }

    // get all
    @GetMapping("/{categoryId}")
    public ResponseEntity<categoryDto> getCategory(@PathVariable Integer categoryId){
        categoryDto getCategory = this.categoryService.getCategory(categoryId);
        return new ResponseEntity<categoryDto>(getCategory, HttpStatus.OK);
    }

    // get by id
    @GetMapping("/")
    public ResponseEntity<List<categoryDto>> getCategories(){
        List<categoryDto> categories = this.categoryService.getCategories();
        return  ResponseEntity.ok(categories);
    }

}
