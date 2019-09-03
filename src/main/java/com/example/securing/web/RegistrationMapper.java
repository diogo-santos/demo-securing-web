package com.example.securing.web;

import com.example.securing.web.domain.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {
    Customer formToCustomer(RegistrationForm form);
}