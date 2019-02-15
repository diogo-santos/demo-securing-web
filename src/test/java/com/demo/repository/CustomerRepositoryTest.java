package com.demo.repository;

import com.demo.entity.Customer;
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
    public void testFindAll() {
        Customer customer = entityManager.persist(Customer.builder()
                                                            .firstName("first")
                                                            .lastName("last")
                                                            .email("email")
                                                            .address1("address1")
                                                            .address2("address2")
                                                            .build());
        List<Customer> customerList = repository.findAll();
        assertThat(customerList).extracting(Customer::getLastName).containsOnly(customer.getLastName());
        assertThat(customerList).extracting(Customer::getFirstName).containsOnly(customer.getFirstName());
        assertThat(customerList).extracting(Customer::getAddress1).containsOnly(customer.getAddress1());
        assertThat(customerList).extracting(Customer::getAddress2).containsOnly(customer.getAddress2());
        assertThat(customerList).extracting(Customer::getEmail).containsOnly(customer.getEmail());
        assertThat(customerList).extracting(Customer::getCountry).containsOnly(customer.getCountry());
        assertThat(customerList).extracting(Customer::getCity).containsOnly(customer.getCity());
        assertThat(customerList).extracting(Customer::getZip).containsOnly(customer.getZip());
        assertThat(customerList).extracting(Customer::getDateOfBirth).containsOnly(customer.getDateOfBirth());
    }
}