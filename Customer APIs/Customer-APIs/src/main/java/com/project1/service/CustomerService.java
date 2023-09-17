package com.project1.service;

import com.project1.payload.CustomerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
        CustomerDto createCustomer(CustomerDto customerDto);
        List<CustomerDto>getAllCustomer(Pageable pageable);
        CustomerDto getCustomerById(long id);
        CustomerDto updateCustomer(CustomerDto customerDto,long id);
        void deleteCustomer(long id);
}
