package com.website.blog.entities;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "post_name", nullable = false)
    private String name;

    @Column(length = 10000)
    private String content;

    private String imageName;

    private Date addDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private category category;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private user user;



}
