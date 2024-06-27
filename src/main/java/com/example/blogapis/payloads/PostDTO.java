package com.example.blogapis.payloads;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Empty;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDTO {

    @NotEmpty
    @Size(min = 10, max = 50)
    private String title;
    @Size(min = 10,max = 50)
    private String contentDescription;

    private Date createdAt;

    private Date updatedAt;

    private String imageName;

    private CategoryDTO category;

    private UserDTO user;

}
