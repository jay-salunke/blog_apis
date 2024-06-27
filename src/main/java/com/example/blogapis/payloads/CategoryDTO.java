package com.example.blogapis.payloads;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

    private int id;

    @NotEmpty
    @Size(min = 10,message = "Minimum 10 characters should added to title")
    private String title;

    @NotEmpty
    @Size(min=30,message = "Minimum 30 characters should be added to the description")
    private String description;

}
