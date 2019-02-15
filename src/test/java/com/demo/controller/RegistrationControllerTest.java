package com.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.demo.controller.RegistrationController.FORM;
import static com.demo.controller.RegistrationController.REGISTRATION;
import static com.demo.controller.RegistrationController.SEARCH;
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
public class RegistrationControllerTest {
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private MockMvc mockMvc;

    private static final String REGISTRATION_PATH   = "/"+REGISTRATION;
    private static final String SEARCH_PATH         = "/"+SEARCH;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void newRegistrationPageTest() throws Exception {
        this.mockMvc.perform(get(REGISTRATION_PATH))
                    .andExpect(content()
                                .string(containsString("Registration")));
    }

    @Test
    public void newRegistrationWhenRequiredFieldIsNull() throws Exception {
        this.mockMvc.perform(post(REGISTRATION_PATH))
                    .andExpect(model()
                            .attributeHasFieldErrors(FORM,"firstName", "lastName", "address1", "city", "country", "zip"))
                    .andExpect(content()
                            .string(containsString("Registration")))
                    .andExpect(view()
                            .name(REGISTRATION));
    }

    @Test
    public void newRegistrationWhenRequiredFieldIsEmpty() throws Exception {
        this.mockMvc.perform(post(REGISTRATION_PATH)
                                .param("firstName", "")
                                .param("lastName", "")
                                .param("address1","")
                                .param("address2","")
                                .param("city","")
                                .param("country","")
                                .param("zip",""))
                    .andExpect(model()
                                .attributeHasFieldErrors(FORM,"firstName", "lastName", "address1", "city", "country", "zip"))
                    .andExpect(content()
                                .string(containsString("Registration")))
                    .andExpect(view()
                                .name(REGISTRATION));
    }

    @Test
    public void newRegistrationWhenDobIsInvalid() throws Exception {
        this.mockMvc.perform(post(REGISTRATION_PATH)
                                .param("dateOfBirth","123"))
                    .andExpect(model()
                                .attributeHasFieldErrors(FORM,"dateOfBirth"))
                    .andExpect(content()
                                .string(containsString("Registration")))
                    .andExpect(view()
                                .name(REGISTRATION));
    }

    @Test
    public void newRegistrationSuccess() throws Exception {
        this.mockMvc.perform(post(REGISTRATION_PATH)
                                .param("firstName", "name")
                                .param("lastName", "name")
                                .param("address1","address")
                                .param("country","country")
                                .param("city","city")
                                .param("zip","zip"))
                    .andExpect(model()
                                .attributeHasNoErrors(FORM));
    }

    @Test
    public void searchWhenNoResults() throws Exception {
        this.mockMvc.perform(get(SEARCH_PATH))
                    .andExpect(content()
                                .string(containsString("No customer registration has been created")));
    }
}