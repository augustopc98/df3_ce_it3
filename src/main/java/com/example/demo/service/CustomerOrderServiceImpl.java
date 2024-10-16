package com.example.demo.service;

import com.example.demo.domain.CustomerOrder;
import com.example.demo.domain.Payment;
import com.example.demo.domain.Discount;
import com.example.demo.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.math.BigDecimal;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderRepository orderRepository;

    @Override
    public void placeOrder(CustomerOrder order) {
        if(order.isOrderValid()) {
            orderRepository.save(order);
        }
    }

    @Override
    public CustomerOrder getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public List<CustomerOrder> getOrdersByCustomer(String customerEmail) {
        return orderRepository.findByCustomerEmail(customerEmail);
    }

    @Override
    public void updateOrder(CustomerOrder order) {
        orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public void processOrderPayment(Long orderId, Payment payment) {
        CustomerOrder order = orderRepository.findById(orderId).orElse(null);
        if(order != null && payment != null) {
            payment.processPayment();
        }
    }

    @Override
    public void applyDiscountToOrder(Long orderId, Discount discount) {
        CustomerOrder order = orderRepository.findById(orderId).orElse(null);
        if(order != null) {
            BigDecimal discountedTotal = discount.applyDiscount(order.calculateTotal());
            // lógica para manejar la aplicación del descuento
        }
    }
}
