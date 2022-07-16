package com.personalproject.blogapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="posts")
@NoArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name="post_title",length = 100,nullable = false)
    private String title;

    @Column(length = 1000)
    private String content;

    private String imageName;
    private Date addedDate;

    @ManyToOne()
    private Category category;

    @ManyToOne()
    private User user;


}
