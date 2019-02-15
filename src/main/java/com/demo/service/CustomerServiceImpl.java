package com.demo.service;

import com.demo.dto.CustomerDTO;
import com.demo.entity.Customer;
import com.demo.form.RegistrationForm;
import com.demo.mapper.RegistrationMapper;
import com.demo.repository.CustomerRepository;
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

    public List<CustomerDTO> find() {
        return repository.findBy();
    }
}
