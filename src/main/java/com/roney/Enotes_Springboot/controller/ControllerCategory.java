package com.roney.Enotes_Springboot.controller;

import com.roney.Enotes_Springboot.model.Category;
import com.roney.Enotes_Springboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class ControllerCategory {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<?> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/category")
    public ResponseEntity<?> saveCategory(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }
}
