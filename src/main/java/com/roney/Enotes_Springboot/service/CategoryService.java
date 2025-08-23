package com.roney.Enotes_Springboot.service;

import com.roney.Enotes_Springboot.dto.CategoryDto;
import com.roney.Enotes_Springboot.dto.CategoryResponse;
import com.roney.Enotes_Springboot.exception.ResourceNotFoundException;
import com.roney.Enotes_Springboot.model.Category;
import com.roney.Enotes_Springboot.repository.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<?> saveCategory(Category cat) {
        List<Category> cats = categoryRepo.findAll();
        if (cats.stream()
                .anyMatch(cp -> cp.getName().equals(cat.getName()))) {
            System.out.println("entering the update block");
            return updateCategory(cat);
        } else {
            try {
                System.out.println("entering the new block");
                cat.setIsActive(true);
                cat.setCreatedBy(1);
                cat.setCreatedDate(new Date());
                cat.setIsDeleted(false);
                Category category1 = categoryRepo.save(cat);
                return ResponseEntity.ok(category1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private ResponseEntity<?> updateCategory(Category cat) {
        System.out.println("hiee");
        Optional<Category> category = categoryRepo.findByName(cat.getName());
        System.out.println(category.isPresent());
        if(category.isPresent()) {
            Category cats = category.get();
            cats.setDescription(cat.getDescription());
//            cats.setIsActive(cat.getIsActive());
//            cats.setIsDeleted(cat.getIsDeleted());
//            cats.setCreatedBy(cat.getCreatedBy());
//            cats.setCreatedDate(cat.getCreatedDate());

            cats.setUpdateBy(1);
            cats.setUpdatedDate(new Date());
            Category updated = categoryRepo.save(cats);
            return ResponseEntity.ok(updated);
        }
        else
            return ResponseEntity.notFound().build();
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

    public ResponseEntity<?> getCategoryByID(int id) throws Exception {
        Category cat = categoryRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()-> new ResourceNotFoundException("Category not found with this id"));

        if(!ObjectUtils.isEmpty(cat)){
            CategoryDto categoryResponse = mapper.map(cat,CategoryDto.class);
            return ResponseEntity.ok(categoryResponse);
        }
        return null;
    }

    public ResponseEntity<?> DeleteCategoryByID(int id) {
        Optional<Category> cat = categoryRepo.findById(id);
        if(cat.isPresent()){
            Category category = cat.get();
            category.setIsDeleted(true);
            category.setIsActive(false);
            categoryRepo.save(category);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
