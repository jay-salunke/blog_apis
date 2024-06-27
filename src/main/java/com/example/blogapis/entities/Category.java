package com.example.blogapis.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name="categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {
      @Id
      @GeneratedValue(strategy = GenerationType.AUTO)
      private Integer id;
      @Column(name = "title" ,length = 150,nullable = false)
      private String categoryTitle;
      @Column(name="description",length = 1000,nullable = false)
      private String categoryDescription;

      @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
      Set<Post> posts = new HashSet<>();
}
