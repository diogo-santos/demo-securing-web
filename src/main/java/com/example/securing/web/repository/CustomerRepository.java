package com.example.securing.web.repository;

import com.example.securing.web.dto.CustomerDTO;
import com.example.securing.web.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<CustomerDTO> findBy();
}
