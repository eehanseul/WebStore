package com.example.webstore.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Orders {
    @Id
    private int id; // 주문 id

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate; // 주문 날짜

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

}
