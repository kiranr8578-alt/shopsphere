package com.shopsphere.repository;

import com.shopsphere.entity.Category;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

//    @Query("SELECT c FROM Category c JOIN FETCH c.products WHERE c.id = :id")
//    @EntityGraph(attributePaths = "products")
//    Category getCategoryById(@Param("id") Long id);
}