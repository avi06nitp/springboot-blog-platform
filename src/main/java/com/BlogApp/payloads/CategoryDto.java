package com.BlogApp.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private String categoryTitle;
    private Integer categoryId;
    private String categoryDescription;
}
