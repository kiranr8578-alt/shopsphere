package com.shopsphere.repository;

import com.shopsphere.entity.Category;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = """
    SELECT 
        c.id AS category_id,
        c.name AS category_name,
        p.id AS product_id,
        p.name AS product_name
    FROM categories c
    JOIN products p ON c.id = p.category_id
    WHERE c.id = :id
""", nativeQuery = true)
    Optional<Category> getBycategory(@Param("id") Long id);

//    @Query("SELECT c FROM Category c JOIN FETCH c.products WHERE c.id = :id")
//    @EntityGraph(attributePaths = "products")
//    Category getCategoryById(@Param("id") Long id);
}