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
    public void testFindByLastName() {
        Customer customer = new Customer();
        customer.setFirstName("first");
        customer.setLastName("last");
        customer.setEmail("email");
        customer.setAddress1("address1");
        customer.setAddress2("address2");
        entityManager.persist(customer);

        List<Customer> customerList = repository.findByLastName(customer.getLastName());
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