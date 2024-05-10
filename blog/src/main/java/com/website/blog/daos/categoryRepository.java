package com.website.blog.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.website.blog.entities.category;

public interface categoryRepository extends JpaRepository<category, Integer>{

}
