package com.website.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class postDto {

    private String name;

    private String content;

    private String imageName;

    private Date addDate;

    private categoryDto Category;

    private userDto User;

    private Set<commentDto> comments = new HashSet<>();
}
