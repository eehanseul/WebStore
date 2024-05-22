package com.example.webstore.product;

import com.example.webstore.utils.ApiUtils;
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
    public ResponseEntity<ApiUtils.ApiResult> registerProduct(@Valid @RequestBody ProductDTO productDto) {
        Product requestProduct = productDto.convertToEntity();
        String savedProduct = productService.registerProduct(requestProduct);
        return new ResponseEntity(success(savedProduct),HttpStatus.CREATED);
    }

    // 상품 전체 조회
    @GetMapping("/products")
    public ResponseEntity<List<Product>> findProducts(
            @RequestParam(value = "limit", defaultValue = "4") int limit,
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage
    ) {
        List<Product> products = productService.findProducts(limit, currentPage);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}


