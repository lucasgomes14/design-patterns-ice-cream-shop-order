package com.iceCreamShop.DesignPatterns.repository;

import com.iceCreamShop.DesignPatterns.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
