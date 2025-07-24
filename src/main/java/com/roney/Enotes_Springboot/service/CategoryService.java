package com.roney.Enotes_Springboot.service;

import com.roney.Enotes_Springboot.model.Category;
import com.roney.Enotes_Springboot.repository.CategoryRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class CategoryService {


    @Autowired
    private CategoryRepo categoryRepo;

    public ResponseEntity<?> getAllCategories() {
        List<Category> cat = categoryRepo.findAll();
        if(CollectionUtils.isEmpty(cat)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cat);
    }

    public ResponseEntity<?> saveCategory(Category category) {
        List<Category> cat = categoryRepo.findAll();
        if (cat.stream()
                .anyMatch(cp -> cp.getName().equalsIgnoreCase(category.getName()))) {
            return new ResponseEntity<>("already there", HttpStatus.BAD_REQUEST);
        } else {
            try {
                category.setIsActive(true);
                category.setCreatedBy(1);
                category.setCreatedDate(new Date());
                category.setIsDeleted(false);
                Category category1 = categoryRepo.save(category);
                return ResponseEntity.ok(category1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
