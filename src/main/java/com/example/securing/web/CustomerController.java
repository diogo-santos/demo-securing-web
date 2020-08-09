package com.example.securing.web;

import com.example.securing.web.domain.CustomerDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService service;

    public static final String REGISTRATION = "registration";
    public static final String SEARCH       = "search";

    public CustomerController(CustomerService customerService) {
        this.service = customerService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute(new CustomerForm());
        return REGISTRATION;
    }

    @PostMapping("/registration")
    public String registration(Model model, @Valid CustomerForm form, BindingResult result) {
        if (!result.hasErrors()) {
            service.save(form);
            model.addAttribute(new CustomerForm());
        }
        return REGISTRATION;
    }

    @GetMapping("/search")
    public String search(Model model) {
        List<CustomerDto> customerList = service.findAllCustomers();
        model.addAttribute("customerList", customerList);
        return SEARCH;
    }
}