package com.example.webstore.product;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class ProductDTO {
    @NotBlank(message="상품명을 입력하세요")
    private String name;
    @NotNull(message="상품가격을 입력하세요")
    @PositiveOrZero(message ="가격을 정확히 입력하세요 ")
    private int price;
    private String description;
    @Min(value = 0, message = "categoryId는 0-3 사이 숫자입니다")
    @Max(value = 3, message = "categoryId는 0-3 사이 숫자입니다")
    private int categoryId;

    public Product convertToEntity() {
        return new Product(name,price,description,categoryId);
    }
}
