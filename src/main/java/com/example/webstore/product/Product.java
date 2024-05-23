package com.example.webstore.product;

import com.example.webstore.order.OrderItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Product {
    @Id
    private int id; // 상품 id
    private String name; // 상품명
    private int price; // 상품 가격
    private String description; // 상품 설명
    private int categoryId; // 카테고리 id
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public Product(String name, int price, String description, int categoryId){
        this.name = name;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
    }
    public ProductDTO convertToDto() {
        return new ProductDTO(name,price,description,categoryId);
    }
}


