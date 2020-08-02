package com.example.securing.web.controller;

import com.example.securing.web.CustomerController;
import com.example.securing.web.CustomerRepository;
import com.example.securing.web.domain.CustomerDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("local")
public class CustomerControllerTest {
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    CustomerRepository repository;

    private static final String REGISTRATION_PATH = "/" + CustomerController.REGISTRATION;
    private static final String SEARCH_PATH = "/" + CustomerController.SEARCH;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getRegistrationPageTest() throws Exception {
        this.mockMvc.perform(get(REGISTRATION_PATH))
                .andExpect(content().string(containsString("Registration")));
    }

    @Test
    public void getSearchWhenNoResults() throws Exception {
        this.mockMvc.perform(get(SEARCH_PATH))
                .andExpect(content().string(containsString("No customer registration has been created")));
    }

    @Test
    public void postRegistrationWhenRequiredFieldIsNull() throws Exception {
        // Given
        MockHttpServletRequestBuilder postAction = post(REGISTRATION_PATH);
        // When
        ResultActions resultActions = this.mockMvc.perform(postAction);
        // Then
        resultActions
                .andExpect(content().string(containsString("Registration")))
                .andExpect(view().name(CustomerController.REGISTRATION))
                .andExpect(model().attributeHasFieldErrors(CustomerController.FORM,
                        "firstName", "lastName", "address1", "city", "country", "zip"));

    }

    @Test
    public void postRegistrationWhenRequiredFieldIsEmpty() throws Exception {
        // Given
        MockHttpServletRequestBuilder postAction = post(REGISTRATION_PATH)
                .param("firstName", "")
                .param("lastName", "")
                .param("address1", "")
                .param("address2", "")
                .param("city", "")
                .param("country", "")
                .param("zip", "");
        // When
        ResultActions resultActions = this.mockMvc.perform(postAction);
        // Then
        resultActions
                .andExpect(content().string(containsString("Registration")))
                .andExpect(view().name(CustomerController.REGISTRATION))
                .andExpect(model().attributeHasFieldErrors(CustomerController.FORM,
                        "firstName", "lastName", "address1", "city", "country", "zip"));

    }

    @Test
    public void postRegistrationWhenDobIsInvalid() throws Exception {
        // Given
        MockHttpServletRequestBuilder postAction = post(REGISTRATION_PATH)
                .param("dateOfBirth", "123");
        // When
        ResultActions resultActions = this.mockMvc.perform(postAction);
        // Then
        resultActions
                .andExpect(content().string(containsString("Registration")))
                .andExpect(view().name(CustomerController.REGISTRATION))
                .andExpect(model().attributeHasFieldErrors(CustomerController.FORM, "dateOfBirth"));
    }

    @Test
    public void postRegistrationSuccess() throws Exception {
        // Given
        MockHttpServletRequestBuilder postAction = post(REGISTRATION_PATH)
                .param("firstName", "name first")
                .param("lastName", "name last")
                .param("address1", "address")
                .param("country", "country")
                .param("email", "test@email.com")
                .param("city", "city")
                .param("zip", "zip");
        // When
        ResultActions resultActions = this.mockMvc.perform(postAction);
        // Then
        resultActions.andExpect(model().attributeHasNoErrors(CustomerController.FORM));

        List<CustomerDto> customerList = repository.findAllCustomersBy();
        assertThat(customerList).hasSize(1);
        assertThat(customerList).extracting(CustomerDto::getLastName).containsOnly("name last");
        assertThat(customerList).extracting(CustomerDto::getFirstName).containsOnly("name first");
        assertThat(customerList).extracting(CustomerDto::getEmail).containsOnly("test@email.com");
    }
}