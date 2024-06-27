package com.example.blogapis.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.blogapis.entities.Category;
import com.example.blogapis.entities.User;
import java.util.Date;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "post_title",nullable = false,length = 150)
    private String title;

    @Column(name = "content_descpriton",nullable = false,length = 150)
    private String contentDescription;


    @Column(name = "post_create_at",nullable = false)
    private Date createdAt;

    @Column(name = "post_updated_at",nullable = false)
    private Date updatedAt;


    @Column(name = "image_name",nullable = false)
    private String imageName;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;
}
