package com.example.demo.domain;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discountId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private CustomerOrder order;

    private BigDecimal discountPercentage;

    // Constructors, Getters, and Setters

    public BigDecimal applyDiscount(BigDecimal totalAmount) {
        return totalAmount.subtract(totalAmount.multiply(discountPercentage.divide(BigDecimal.valueOf(100))));
    }
}
