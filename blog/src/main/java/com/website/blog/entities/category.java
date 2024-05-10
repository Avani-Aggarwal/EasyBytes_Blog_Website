package com.website.blog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name = "Name", length = 100, nullable = false)
    private String categoryName;

    // @OneToMany(mappedBy = "Category", cascade = CascadeType.ALL)
    // private List<post>  posts = new ArrayList<>();
}
