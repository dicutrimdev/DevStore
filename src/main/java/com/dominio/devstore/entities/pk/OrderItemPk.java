package com.dominio.devstore.entities.pk;

import lombok.*;
import jakarta.persistence.*;
import com.dominio.devstore.entities.Order;
import com.dominio.devstore.entities.Product;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class OrderItemPk {
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
