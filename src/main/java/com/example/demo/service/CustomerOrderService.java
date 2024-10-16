package com.example.demo.service;

import com.example.demo.domain.CustomerOrder;
import com.example.demo.domain.Payment;
import com.example.demo.domain.Discount;

import java.util.List;

public interface CustomerOrderService {
    void placeOrder(CustomerOrder order);
    CustomerOrder getOrderById(Long orderId);
    List<CustomerOrder> getOrdersByCustomer(String customerEmail);
    void updateOrder(CustomerOrder order);
    void cancelOrder(Long orderId);
    void processOrderPayment(Long orderId, Payment payment);
    void applyDiscountToOrder(Long orderId, Discount discount);
}
