package com.example.demo.controller;

import com.example.demo.domain.CustomerOrder;
import com.example.demo.domain.Payment;
import com.example.demo.domain.Discount;

import com.example.demo.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/orders")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService orderService;

    @PostMapping
    public void placeOrder(@RequestBody CustomerOrder order) {
        orderService.placeOrder(order);
    }

    @GetMapping("/{id}")
    public CustomerOrder getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/customer/{email}")
    public List<CustomerOrder> getOrdersByCustomer(@PathVariable String email) {
        return orderService.getOrdersByCustomer(email);
    }

    @PutMapping("/{id}")
    public void updateOrder(@PathVariable Long id, @RequestBody CustomerOrder order) {
        order.setId(id); // Make sure the ID matches the path variable
        orderService.updateOrder(order);
    }

    @DeleteMapping("/{id}")
    public void cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
    }

    // New endpoint for processing payment
    @PostMapping("/{id}/payment")
    public void processPayment(@PathVariable Long id, @RequestBody Payment payment) {
        orderService.processOrderPayment(id, payment);
    }

    // New endpoint for applying discount
    @PostMapping("/{id}/apply-discount")
    public void applyDiscount(@PathVariable Long id, @RequestBody Discount discount) {
        orderService.applyDiscountToOrder(id, discount);
    }
}
