package com.example.blogapis.services.impl;


import com.example.blogapis.entities.Category;
import com.example.blogapis.exception.ResourceNotFoundException;
import com.example.blogapis.payloads.CategoryDTO;
import com.example.blogapis.repositories.CategoryRepo;
import com.example.blogapis.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public CategoryDTO createCategory(CategoryDTO category) {
        Category cat = modelMapper.map(category,Category.class);
        categoryRepo.save(cat);
        return modelMapper.map(cat, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO category, Integer id) {

        Category cat = categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("category","id",id));

        cat.setId(id);
        cat.setCategoryTitle(category.getTitle());
        cat.setCategoryDescription(category.getDescription());
        categoryRepo.save(cat);
        return modelMapper.map(cat, CategoryDTO.class);
    }

    @Override
    public CategoryDTO getCategory(Integer id) {
        Category cat = categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("category","id",id));
        return modelMapper.map(cat, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        return categories.stream().map(category-> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Integer id) {
        Category cat = categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("category","id",id));
        categoryRepo.delete(cat);
    }


}
