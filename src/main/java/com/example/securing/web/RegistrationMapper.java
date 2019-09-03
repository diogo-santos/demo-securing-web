package com.example.securing.web;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {
    Customer formToCustomer(RegistrationForm form);
}