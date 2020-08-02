package com.example.securing.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CustomerController {

    private final CustomerService service;

    public static final String REGISTRATION = "registration";
    public static final String SEARCH       = "search";
    public static final String FORM         = "customerForm";

    public CustomerController(CustomerService customerService) {
        this.service = customerService;
    }

    @GetMapping(REGISTRATION)
    public String registration(Model model) {
        model.addAttribute(new CustomerForm());
        return REGISTRATION;
    }

    @PostMapping(REGISTRATION)
    public String registration(Model model, @Valid CustomerForm form, BindingResult result) {
        if (!result.hasErrors()) {
            service.save(form);
            model.addAttribute(new CustomerForm());
        }
        return REGISTRATION;
    }

    @GetMapping(SEARCH)
    public String search(Model model) {
        model.addAttribute("list", service.findAllCustomers());
        return SEARCH;
    }
}