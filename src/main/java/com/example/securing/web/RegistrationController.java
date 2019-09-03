package com.example.securing.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final CustomerService service;

    public static final String REGISTRATION = "registration";
    public static final String SEARCH       = "search";
    public static final String FORM         = "registrationForm";

    public RegistrationController(CustomerService customerService) {
        this.service = customerService;
    }

    @GetMapping(REGISTRATION)
    public String registration(Model model) {
        model.addAttribute(FORM, new RegistrationForm());
        return REGISTRATION;
    }

    @PostMapping(REGISTRATION)
    public String registration(Model model, @Valid RegistrationForm form, BindingResult result) {
        if (!result.hasErrors()) {
            service.save(form);
            model.addAttribute(new RegistrationForm());
        }
        return REGISTRATION;
    }

    @GetMapping(SEARCH)
    public String search(Model model) {
        model.addAttribute("list", service.find());
        return SEARCH;
    }
}