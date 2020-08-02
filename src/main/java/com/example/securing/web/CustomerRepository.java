package com.example.securing.web;

import com.example.securing.web.domain.Customer;
import com.example.securing.web.domain.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<CustomerDto> findAllCustomersBy();
}