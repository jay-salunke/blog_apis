package com.example.blogapis.services;


import com.example.blogapis.payloads.CategoryDTO;
import java.util.List;

public interface CategoryService {

    //create
    public CategoryDTO createCategory(CategoryDTO category);

    //update
    public CategoryDTO updateCategory(CategoryDTO category,Integer id);
    //get
    public CategoryDTO getCategory(Integer id);
    //getAll
    public List<CategoryDTO> getAllCategories();

    //delete
    public void deleteCategory(Integer id);

}
