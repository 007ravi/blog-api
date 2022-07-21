package com.personalproject.blogapi.payloads;

import com.personalproject.blogapi.models.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private Integer id;

    private String content;
}
