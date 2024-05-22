package com.example.webstore.product;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    ProductRepository productRepository;
    @Transactional
    public String registerProduct(Product product) {
        productRepository.save(product);
        return productRepository.findById(product.getId()).getName();
    }

    @Transactional
    public List<Product> findProducts(int limit, int currentPage) {
        Pageable pageable = PageRequest.of(currentPage - 1, limit);
        Page<Product> productsPage;

        productsPage = productRepository.findAllProducts(pageable);
        return productsPage.getContent();
    }
}
