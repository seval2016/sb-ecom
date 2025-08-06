package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author seval
 */

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //@RequestMapping(value="/public/categories",  method=RequestMethod.GET)
    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>>  getCategoryList() {
        List<Category> categories = categoryService.getAllCategories();
        return  new ResponseEntity<>(categories , HttpStatus.OK);
    }


    //Add Category
    //@RequestMapping(value = "/public/categories",  method = RequestMethod.POST)
    @PostMapping("/public/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {
        categoryService.createCategory(category);
        return  new ResponseEntity<>("Category added successfully",HttpStatus.CREATED);
    }

    //@RequestMapping(value = "/admin/categories/{categoryId}",   method = RequestMethod.GET)
    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        try {
            String status = categoryService.deleteCategory(categoryId);
            return ResponseEntity.status(HttpStatus.OK).body(status);
        } catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    //@RequestMapping(value = "/public/categories/{categoryId}",method = RequestMethod.PUT)
    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category,
                                                 @PathVariable Long categoryId){
        try {
            Category updatedCategory= categoryService.updateCategory(category,categoryId);
            return new ResponseEntity<>("Updated Category with category id: " + categoryId, HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }


    }
}
