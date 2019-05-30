package com.example.securing.web.mapper;

import com.example.securing.web.entity.Customer;
import com.example.securing.web.form.RegistrationForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {
    Customer formToCustomer(RegistrationForm form);
}