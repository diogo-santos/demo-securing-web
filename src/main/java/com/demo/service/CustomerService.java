package com.demo.service;

import com.demo.form.RegistrationForm;

import java.util.List;

public interface CustomerService {
    void save(RegistrationForm form);

    List search();
}
