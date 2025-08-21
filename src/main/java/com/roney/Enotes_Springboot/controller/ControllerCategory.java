package com.roney.Enotes_Springboot.controller;

import com.roney.Enotes_Springboot.dto.CategoryDto;
import com.roney.Enotes_Springboot.model.Category;
import com.roney.Enotes_Springboot.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class ControllerCategory {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/category")
    public ResponseEntity<?> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/category")
    public ResponseEntity<?> saveCategory(@RequestBody Category cat){
        CategoryDto category = mapper.map(cat,CategoryDto.class);
        return categoryService.saveCategory(category);
    }

    @GetMapping("/active-category")
    public ResponseEntity<?> getActiveCategory(){
        return categoryService.getActiveCategory();
    }

    @GetMapping("{id")
    public ResponseEntity<?> getCategoryById(@PathVariable int id) throws Exception {
        return categoryService.getCategoryByID(id);
    }
    @GetMapping("{id")
    public ResponseEntity<?> DeleteCategoryById(@PathVariable int id){
        return categoryService.DeleteCategoryByID(id);
    }
}
