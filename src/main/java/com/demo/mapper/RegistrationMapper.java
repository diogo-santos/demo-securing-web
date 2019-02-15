package com.demo.mapper;

import com.demo.entity.Customer;
import com.demo.form.RegistrationForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {
    Customer formToCustomer(RegistrationForm form);
}