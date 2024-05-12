package com.website.blog.entities;

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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class comment {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    private String commentText;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private post post;


}
