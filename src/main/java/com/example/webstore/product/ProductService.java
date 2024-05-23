package com.example.webstore.product;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@Transactional
public class ProductService {
    ProductRepository productRepository;

    public String registerProduct(Product product) {
        productRepository.save(product);
        return productRepository.findById(product.getId()).get().getName();
    }


    public List<Product> findProducts(int limit, int currentPage) {
        Pageable pageable = PageRequest.of(currentPage - 1, limit);
        Page<Product> productsPage;

        productsPage = productRepository.findAllProducts(pageable);
        return productsPage.getContent();
    }

    public Product findProduct(int id) {
        return productRepository.findById(id).get();
    }

    public void deleteProduct(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Product not found with id: " + id);
        }
    }
}
