package com.example.demo.repository;

import com.example.demo.domain.CustomerOrder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerOrderRepositoryH2Impl implements CustomerOrderRepository {
    private final List<CustomerOrder> orderList = new ArrayList<>();

    @Override
    public void save(CustomerOrder order) {
        orderList.add(order);
    }

    @Override
    public CustomerOrder findById(Long orderId) {
        return orderList.stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<CustomerOrder> findByCustomerEmail(String email) {
        return orderList.stream()
                .filter(order -> order.getCustomerEmail().equals(email))
                .toList();
    }

    @Override
    public void delete(Long orderId) {
        orderList.removeIf(order -> order.getId().equals(orderId));
    }

    @Override
    public void update(CustomerOrder order) {
        delete(order.getId());
        save(order);
    }
}
