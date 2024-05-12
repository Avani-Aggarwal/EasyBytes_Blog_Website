package com.website.blog.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.website.blog.entities.comment;

public interface commentRepository extends JpaRepository<comment, Integer> {

}
