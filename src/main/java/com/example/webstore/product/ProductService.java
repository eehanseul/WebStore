package com.example.webstore.product;

import com.example.webstore.exception.DuplicateException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ProductService {
    ProductRepository productRepository;

    public String registerProduct(ProductDTO productDto) {
        Product requestProduct = productDto.convertToEntity();
        productRepository.save(requestProduct);
        return productRepository.findById(requestProduct.getId()).get().getName();
    }

    public List<ProductDTO> findProducts(int limit, int currentPage) {
        Pageable pageable = PageRequest.of(currentPage - 1, limit);
        List<Product> resProducts = productRepository.findAll(pageable).getContent();
        return resProducts.stream()
                .map(Product::convertToDto)
                .collect(Collectors.toList());
    }

    public ProductDTO findProduct(int id) {
        Product resProduct = productRepository.findById(id).get();
        return resProduct.convertToDto();
    }

    public void deleteProduct(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Product not found with id: " + id);
        }
    }

    public void checkDuplicateProduct(String name) {
        Product existProduct = productRepository.findByName(name);
        if(existProduct!=null)
            throw new DuplicateException("중복 상품");
    }
}
