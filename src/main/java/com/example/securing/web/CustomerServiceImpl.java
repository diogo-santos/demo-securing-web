package com.example.securing.web;

import com.example.securing.web.domain.Customer;
import com.example.securing.web.domain.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final RegistrationMapper mapper;

    public CustomerServiceImpl(CustomerRepository repository, RegistrationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void save(RegistrationForm form) {
        Customer customer = this.mapper.formToCustomer(form);
        repository.save(customer);
    }

    public List<CustomerDto> find() {
        return repository.findBy();
    }
}
