// CustomerOrder.java
package com.example.demo.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Generar ID automáticamente
    private Long id;

    private String customerEmail;
    private String customerAddress;
    private Date orderDate;
    private String deliveryStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Payment> payments;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Discount> discounts;

    public CustomerOrder(Long id, String customerEmail, String customerAddress, Date orderDate, List<OrderItem> items) {
        this.id = id;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.orderDate = orderDate;
        this.items = items;
    }

    public CustomerOrder() {}

    public void addOrderItem(OrderItem item) {
        items.add(item);
    }

    public void removeOrderItem(OrderItem item) {
        items.remove(item);
    }

    public BigDecimal calculateTotal() {
        return items.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    public boolean isOrderValid() {
        return customerEmail != null && !items.isEmpty();
    }
}
