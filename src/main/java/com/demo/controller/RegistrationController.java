package com.demo.controller;

import com.demo.form.RegistrationForm;
import com.demo.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private CustomerService customerService;

    public RegistrationController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute(new RegistrationForm());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(Model model, @Valid RegistrationForm form, BindingResult result) {
        if (!result.hasErrors()) {
            customerService.save(form);
            model.addAttribute(new RegistrationForm());
        }
        return "registration";
    }

    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("list", customerService.search());
        return "search";
    }
}
