package com.example.blogapis.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {

    private List<PostDTO> content;

    private int pageNumber;

    private int pageSize;

    //how many records
    private int totalElements;

    //total pages for pagination
    private int totalPages;

    // checking if its last page
    private boolean lastPage;

}
