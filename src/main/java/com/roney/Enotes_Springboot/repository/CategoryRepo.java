package com.roney.Enotes_Springboot.repository;

import com.roney.Enotes_Springboot.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {

    List<Category> findByIsActiveTrue();
}
