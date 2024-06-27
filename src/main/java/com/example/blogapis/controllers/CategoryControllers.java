package com.example.blogapis.controllers;

import com.example.blogapis.payloads.APIResponse;
import com.example.blogapis.payloads.CategoryDTO;
import com.example.blogapis.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryControllers {

    @Autowired
    CategoryService categoryService;
    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO category){
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategory(@Valid @PathVariable Integer categoryId){
        return new ResponseEntity<>(categoryService.getCategory(categoryId),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO category,@PathVariable Integer categoryId){
        return new ResponseEntity<>(categoryService.updateCategory(category,categoryId),HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<APIResponse> deleteCategory(@PathVariable Integer categoryId){
       categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(new APIResponse("categoryId: "+categoryId+" deleted successfully",true,HttpStatus.OK.toString()));
    }

}
