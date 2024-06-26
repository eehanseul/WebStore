package com.example.webstore.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    //@Query("SELECT p FROM Product p WHERE p.name = :name")
    Product findByName(String name);


//    // 특정 카테고리의 제품을 페이징하여 조회
//    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
//    Page<Product> findProductsByCategoryId(Pageable pageable, int categoryId);
}
