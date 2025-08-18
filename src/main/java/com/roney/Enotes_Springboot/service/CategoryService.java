package com.roney.Enotes_Springboot.service;

import com.roney.Enotes_Springboot.dto.CategoryDto;
import com.roney.Enotes_Springboot.dto.CategoryResponse;
import com.roney.Enotes_Springboot.model.Category;
import com.roney.Enotes_Springboot.repository.CategoryRepo;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper mapper;

    public ResponseEntity<?> getAllCategories() {
        List<Category> cat = categoryRepo.findAll();
        List<CategoryDto> categoryDtoList = cat.stream().map(cate->mapper.map(cate,CategoryDto.class)).toList();
        if(CollectionUtils.isEmpty(categoryDtoList)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryDtoList);
    }

    public ResponseEntity<?> saveCategory(CategoryDto cat) {
        List<Category> cats = categoryRepo.findAll();
        if (cats.stream()
                .anyMatch(cp -> cp.getName().equalsIgnoreCase(cat.getName()))) {
            return new ResponseEntity<>("already there", HttpStatus.BAD_REQUEST);
        } else {
            try {
                Category category = mapper.map(cat, Category.class);
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

    public ResponseEntity<?> getActiveCategory() {
        List<Category> cat = categoryRepo.findByIsActiveTrue();
        List<CategoryResponse> categoryResponseList = cat.stream().map(cate->mapper.map(cate,CategoryResponse.class)).toList();
        if(categoryResponseList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(categoryResponseList);
        }
    }
}
