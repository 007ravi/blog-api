package com.personalproject.blogapi.payloads;

import com.personalproject.blogapi.models.Category;
import com.personalproject.blogapi.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private CategoryDto category;//when we use category dto instead of category then infinite recursion will not be there
    private UserDto user;//userDto dont have post in it so it dont result in infinite json
}
