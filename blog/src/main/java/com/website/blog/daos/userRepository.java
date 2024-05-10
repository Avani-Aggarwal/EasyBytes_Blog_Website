package com.website.blog.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.website.blog.entities.user;

public interface userRepository extends JpaRepository< user, Integer> {

}
