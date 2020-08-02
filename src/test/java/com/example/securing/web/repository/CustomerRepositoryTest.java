package com.example.securing.web.repository;

import com.example.securing.web.domain.Customer;
import com.example.securing.web.domain.CustomerDto;
import com.example.securing.web.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CustomerRepository repository;

    @Test
    public void testFindAllCustomer() {
        // Given
        Customer customerToSave = Customer.builder()
                .firstName("firstDto")
                .lastName("lastDto")
                .email("emailDto")
                .address1("address1Dto")
                .address2("address2Dto")
                .build();
        // When
        Customer customer = entityManager.persist(customerToSave);
        // Then
        List<CustomerDto> customerList = repository.findAllCustomersBy();
        assertThat(customerList).extracting(CustomerDto::getLastName).containsOnly(customer.getLastName());
        assertThat(customerList).extracting(CustomerDto::getFirstName).containsOnly(customer.getFirstName());
        assertThat(customerList).extracting(CustomerDto::getId).containsOnly(customer.getId());
        assertThat(customerList).extracting(CustomerDto::getEmail).containsOnly(customer.getEmail());
    }
}