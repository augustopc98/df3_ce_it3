// CustomerOrderRepository.java
package com.example.demo.repository;

import com.example.demo.domain.CustomerOrder;
import java.util.List;

public interface CustomerOrderRepository {
    void save(CustomerOrder order);
    CustomerOrder findById(Long orderId);
    List<CustomerOrder> findByCustomerEmail(String email);
    void delete(Long orderId);
    void update(CustomerOrder order);
}
