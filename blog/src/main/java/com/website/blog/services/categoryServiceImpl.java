package com.website.blog.services;

import  com.website.blog.daos.categoryRepository;
import com.website.blog.entities.category;
import com.website.blog.exceptions.ResourceNotFoundException;
import com.website.blog.payloads.categoryDto;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class categoryServiceImpl implements categoryService {

    @Autowired
    private categoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public categoryDto createCategory(categoryDto CateryDto) {
       category cat =  this.modelMapper.map(CateryDto, category.class);
       category addedCategory = this.categoryRepository.save(cat);
       return this.modelMapper.map(addedCategory, categoryDto.class);
    }

    @Override
    public categoryDto updateCategory(categoryDto CateryDto, Integer categoryId) {
        category cat = this.categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("category", "Category Id", categoryId));
        cat.setCategoryName(CateryDto.getCategoryName());

        category updCategory = this.categoryRepository.save(cat);
        return this.modelMapper.map(updCategory, categoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        category cat = this.categoryRepository.findById(categoryId)
                 .orElseThrow(()-> new ResourceNotFoundException("category", "Category Id", categoryId));
        this.categoryRepository.delete(cat);
    }

    @Override
    public categoryDto getCategory(Integer categoryId) {
        category cat = this.categoryRepository.findById(categoryId)
                 .orElseThrow(()-> new ResourceNotFoundException("category", "Category Id", categoryId));
         return this.modelMapper.map(cat, categoryDto.class);
    }

    @Override
    public List<categoryDto> getCategories() {
        List<category> categories = this.categoryRepository.findAll();
        List<categoryDto> catDtos = categories.stream().map((cat)-> this.modelMapper.map(cat, categoryDto.class))
                             .collect(Collectors.toList());
        return catDtos;
    }

}
