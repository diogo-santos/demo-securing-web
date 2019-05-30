package com.example.securing.web.service;

import com.example.securing.web.form.RegistrationForm;
import java.util.List;

public interface CustomerService {
    void save(RegistrationForm form);
    List find();
}