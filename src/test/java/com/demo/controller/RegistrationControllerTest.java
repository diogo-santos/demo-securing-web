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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("local")
public class RegistrationControllerTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void newRegistrationPageTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/registration"))
                    .andExpect(content().string(containsString("Registration")));
    }

    @Test
    public void newRegistrationWhenRequiredFieldIsEmpty() throws Exception {
        MockHttpServletRequestBuilder newRegistration = post("/registration")
                                                        .param("firstName", "")
                                                        .param("lastName", "")
                                                        .param("address1","")
                                                        .param("address2","")
                                                        .param("city","")
                                                        .param("country","")
                                                        .param("zip","");

        this.mockMvc.perform(newRegistration).andExpect(model().attributeHasFieldErrors("registrationForm",
                                                                                        "firstName", "lastName", "address1", "city", "country", "zip"))
                                             .andExpect(content().string(containsString("Registration")));
    }

    @Test
    public void newRegistrationWhenDobIsInvalid() throws Exception {
        MockHttpServletRequestBuilder newRegistration = post("/registration").param("dateOfBirth","123");

        this.mockMvc.perform(newRegistration).andExpect(model().attributeHasFieldErrors("registrationForm","dateOfBirth"))
                                             .andExpect(content().string(containsString("Registration")));
    }

    @Test
    public void newRegistrationSuccess() throws Exception {
        MockHttpServletRequestBuilder newRegistration = post("/registration")
                                                        .param("firstName", "name")
                                                        .param("lastName", "name")
                                                        .param("address1","address")
                                                        .param("country","country")
                                                        .param("city","city")
                                                        .param("zip","zip");

        this.mockMvc.perform(newRegistration).andExpect(model().attributeHasNoErrors("registrationForm"));
    }

    @Test
    public void searchWhenNoResults() throws Exception {
        this.mockMvc.perform(get("/search"))
                    .andExpect(content().string(containsString("No customer registration has been created.")));
    }
}