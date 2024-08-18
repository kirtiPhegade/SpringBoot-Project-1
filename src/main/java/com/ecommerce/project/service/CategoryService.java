package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService{

    private List<Category> categories = new ArrayList<Category>();

    long auto_cateroryid=101;
    @Override
    public List<Category> getAllCategories(){
        return categories;
    }

    @Override
    public void createCategory(Category category){
        category.setCategoryID(auto_cateroryid++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long id){
        Category category = categories.stream()
                .filter(catobj -> catobj.getCategoryID().equals(id))
                .findFirst().orElse(null);

        if(category== null){
            return "Category not found";
        }else {
            categories.remove(category);
            return "Category deleted";
        }
    }

    public String updateCategory(Long id , Category category){
        Category categoryOld = categories.stream()
                .filter(c -> c.getCategoryID().equals(id))
                .findFirst().orElse(null);
        if(id==categoryOld.getCategoryID()){
            categoryOld.setCategoryName(category.getCategoryName());
            return "Category updated";
        }
        else {
            return "Category not found";
        }
    }
}
