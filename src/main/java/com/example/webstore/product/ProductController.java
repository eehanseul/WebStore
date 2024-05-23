package com.example.webstore.product;

import com.example.webstore.utils.Validator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.webstore.utils.ApiUtils.success;

@Slf4j
@RestController
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    // 상품 개별 등록
    @PostMapping("/products")
    public ResponseEntity registerProduct(@Valid @RequestBody ProductDTO productDto) {
        Product requestProduct = productDto.convertToEntity();
        String savedProduct = productService.registerProduct(requestProduct);
        return new ResponseEntity(success(savedProduct), HttpStatus.CREATED);
    }

    // 상품 전체 조회
    @GetMapping("/products")
    public ResponseEntity findProducts(
            @RequestParam(value = "limit",defaultValue = ""+Integer.MAX_VALUE) int limit,
            @RequestParam(value = "currentPage", defaultValue="1") int currentPage
    ) {
        List<Product> foundProducts = productService.findProducts(limit, currentPage);
        return new ResponseEntity<>(foundProducts, HttpStatus.OK);
    }

    // 상품 개별 조회
    @GetMapping("/products/{id}")
    public ResponseEntity findProduct(@PathVariable("id") int id){
        Product foundProduct = productService.findProduct(id);
        return new ResponseEntity<>(foundProduct, HttpStatus.OK);
    }

    // 상품 개별 삭제
    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") int id) {
        Validator.isPositiveNumber(id);
        productService.deleteProduct(id);
        return new ResponseEntity<>(success("delete success"),HttpStatus.OK);
    }

}


