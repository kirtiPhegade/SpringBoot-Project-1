package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private Category Oldcategory;

/*
findAll() return all
save() to save data in database
 */
    long auto_cateroryid=101;
    @Override
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category){
        /*
        1)save category
         */
        category.setCategoryID(auto_cateroryid++);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long id){
        /*we have id
        1) find category with id
        2) throw exception not_found if id is not present
        3) delete category with delete method
         */
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        categoryRepository.delete(category);
        return "Category deleted";
    }

    public Category updateCategory(Long id , Category category){
        /*
        1) find category with id
        2) throw exception if not found
        3)save category
         */
        Category savedCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        category.setCategoryID(id);
        savedCategory=categoryRepository.save(category);
        return savedCategory;
    }
}
