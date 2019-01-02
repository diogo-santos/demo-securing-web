package com.demo.service;

import com.demo.entity.Customer;
import com.demo.form.RegistrationForm;
import com.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    public void save(RegistrationForm form) {
        Customer customer = Customer.builder()
                                    .firstName(form.getFirstName())
                                    .lastName(form.getLastName())
                                    .email(form.getEmail())
                                    .dateOfBirth(form.getDateOfBirth())
                                    .address1(form.getAddress1())
                                    .address2(form.getAddress2())
                                    .city(form.getCity())
                                    .country(form.getCountry())
                                    .zip(form.getZip())
                                    .build();
        repository.save(customer);
    }

    public List search() {
        return repository.findAll();
    }
}
