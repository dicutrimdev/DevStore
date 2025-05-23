package com.dominio.devstore.entities;

import lombok.*;
import jakarta.persistence.*;
import com.dominio.devstore.entities.pk.OrderItemPk;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "TB_ORDER_ITEM")
public class OrderItem {
    @EmbeddedId
    private OrderItemPk id = new OrderItemPk();
    private BigDecimal price;
    private Integer quantity;

    public OrderItem(Order order, Product product, BigDecimal price, Integer quantity) {
        id.setOrder(order);
        id.setProduct(product);
        this.price = price;
        this.quantity = quantity;
    }

    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }
}
