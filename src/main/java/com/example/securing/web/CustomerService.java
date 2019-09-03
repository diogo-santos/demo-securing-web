package com.example.securing.web;

import java.util.List;

public interface CustomerService {
    void save(RegistrationForm form);
    List find();
}