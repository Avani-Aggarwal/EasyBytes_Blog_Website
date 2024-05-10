package com.website.blog.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import com.website.blog.entities.category;
import com.website.blog.entities.post;
import com.website.blog.entities.user;
import com.website.blog.exceptions.ResourceNotFoundException;
import com.website.blog.payloads.postDto;
import com.website.blog.payloads.postResponse;

import com.website.blog.daos.postRepository;
import com.website.blog.daos.categoryRepository;
import com.website.blog.daos.userRepository;

@Service
public class postServiceImpl implements postService {

    @Autowired
    private postRepository postRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private userRepository userRepository;

    @Autowired
    private categoryRepository categoryRepository;

    @Override
    public postDto createPost(postDto PostDto, Integer userId, Integer categoryId) {

        user User = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "user Id", userId));
        category Category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "category Id", categoryId));

        post Post = this.mapper.map(PostDto, post.class);
        Post.setImageName("defalut.png");
        Post.setAddDate(new Date());
        Post.setUser(User);
        Post.setCategory(Category);

        post newPost =  this.postRepository.save(Post);

        return this.mapper.map(newPost, postDto.class);
    }

    @Override
    public postDto updatePost(postDto PostDto, Integer postId) {
        post Post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "post Id", postId));
        Post.setName(PostDto.getName());
        Post.setContent(PostDto.getContent());
        Post.setImageName(PostDto.getImageName());
        post updatedPost = this.postRepository.save(Post);
       return this.mapper.map(updatedPost, postDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        post Post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "post Id", postId));
       this.postRepository.delete(Post);
    }

    @Override
    public postResponse getALLPosts(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<post> pagePosts = this.postRepository.findAll(pageable);
        List<post> allPosts = pagePosts.getContent();
        List<postDto> postDtos = allPosts.stream().map((post)-> this.mapper.map(post, postDto.class)).collect(Collectors.toList());
        postResponse pResponse = new postResponse();
        pResponse.setContent(postDtos);
        pResponse.setPageNumber(pagePosts.getNumber());
        pResponse.setPageSize(pagePosts.getSize());
        pResponse.setTotalElements(pagePosts.getTotalElements());
        pResponse.setTotalPages(pagePosts.getTotalPages());
        pResponse.setLastPage(pagePosts.isLast());
        return pResponse;
    }

    @Override
    public postDto getPostById(Integer postId) {
       post Post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "post Id", postId));
       return this.mapper.map(Post, postDto.class);
    }

    @Override
    public List<postDto> getPostByCategory(Integer categoryId) {
        category cat = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "category Id", categoryId));
        List<post> posts = this.postRepository.findByCategory(cat);
        List<postDto> postDtos = posts.stream().map((post)-> this.mapper.map(post, postDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<postDto> getPostByUser(Integer userId) {
        user User = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "user Id", userId));
        List<post> posts = this.postRepository.findByUser(User);
        List<postDto> postDtos = posts.stream().map((post)-> this.mapper.map(post, postDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<postDto> searchPost(String keyword) {
       List<post> posts = this.postRepository.searchByTitle("%" + keyword + "%");
       List<postDto> postDtos = posts.stream().map((post)-> this.mapper.map(post, postDto.class)).collect(Collectors.toList());
       return postDtos;
    }

}
