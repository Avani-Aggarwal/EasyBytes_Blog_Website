package com.website.blog.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.website.blog.entities.category;
import com.website.blog.entities.post;
import com.website.blog.entities.user;

public interface postRepository extends JpaRepository<post, Integer> {
    List<post> findByCategory(category Category);
    List<post> findByUser(user User);

    @Query("select p from post p where p.name like :key")
    List<post> searchByTitle(@Param("key") String name);

}
