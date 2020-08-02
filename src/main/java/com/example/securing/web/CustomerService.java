package com.example.securing.web;

import com.example.securing.web.domain.Customer;
import com.example.securing.web.domain.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void save(CustomerForm form) {
        Customer customer = Customer.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .address1(form.getAddress1())
                .address2(form.getAddress2())
                .country(form.getCountry())
                .city(form.getCity())
                .zipCode(form.getZipCode())
                .dateOfBirth(form.getDateOfBirth())
                .build();
        repository.save(customer);
    }

    public List<CustomerDto> findAllCustomers() {
        return repository.findAllCustomersBy();
    }
}
