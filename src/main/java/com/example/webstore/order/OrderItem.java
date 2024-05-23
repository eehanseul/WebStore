package com.example.webstore.order;

import com.example.webstore.product.Product;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class OrderItem {
    @Id
    private int id; // 주문 항목 id

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int count; // 주문 수량
}
