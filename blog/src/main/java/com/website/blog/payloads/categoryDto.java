package com.website.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class categoryDto {

    private Integer categoryId;

    @NotBlank
    private String categoryName;
}