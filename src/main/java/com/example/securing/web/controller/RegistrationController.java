package com.example.securing.web.controller;

import com.example.securing.web.form.RegistrationForm;
import com.example.securing.web.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final CustomerService customerService;

    static final String REGISTRATION = "registration";
    static final String SEARCH       = "search";
    static final String FORM         = "registrationForm";

    public RegistrationController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(REGISTRATION)
    public String registration(Model model) {
        model.addAttribute(FORM, new RegistrationForm());
        return REGISTRATION;
    }

    @PostMapping(REGISTRATION)
    public String registration(Model model, @Valid RegistrationForm form, BindingResult result) {
        if (!result.hasErrors()) {
            customerService.save(form);
            model.addAttribute(new RegistrationForm());
        }
        return REGISTRATION;
    }

    @GetMapping(SEARCH)
    public String search(Model model) {
        model.addAttribute("list", customerService.find());
        return SEARCH;
    }
}