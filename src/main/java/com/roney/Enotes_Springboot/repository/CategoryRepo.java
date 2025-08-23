package com.roney.Enotes_Springboot.repository;

import com.roney.Enotes_Springboot.model.Category;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {

    List<Category> findByIsActiveTrue();

    Optional<Category> findByIdAndIsDeletedFalse(int id);

    Optional<Category> findByName(@NotEmpty String name);
}
