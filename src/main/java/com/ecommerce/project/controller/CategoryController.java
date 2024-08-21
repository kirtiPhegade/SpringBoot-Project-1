package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("api/public/categories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping("api/public/categories")
    public String createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return "Category created";
    }

    @DeleteMapping("api/admin/categories/{categoryID}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryID){
        try {
            String status = categoryService.deleteCategory(categoryID);
            return new ResponseEntity<>(status, HttpStatus.OK);
        }catch(ResponseStatusException e){
            return new ResponseEntity<>("Category is not found",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("api/admin/categories/{categoryID}")
    public ResponseEntity<String> updateCategory(@PathVariable Long categoryID, @RequestBody Category category){
        try {
            categoryService.updateCategory(categoryID, category);
            return new ResponseEntity<>("Category is updated", HttpStatus.OK);
        }catch(ResponseStatusException e){
            return new ResponseEntity<>("Category is not found",e.getStatusCode());
        }
    }
}
