package com.project1.service.impl;


import com.project1.entiry.Customer;
import com.project1.exception.ResourceNotFoundException;
import com.project1.payload.CustomerDto;
import com.project1.repository.CustomerRepository;
import com.project1.service.CustomerService;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; // Correct the import statement
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private ModelMapper mapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper= mapper;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = mapToEntity(customerDto);
        Customer newCustomer = customerRepository.save(customer);

        return mapToDto(newCustomer);
    }

    private CustomerDto mapToDto(Customer newCustomer) {
        return mapper.map(newCustomer,CustomerDto.class);
    }

  private   Customer mapToEntity(CustomerDto customerDto) {
         Customer customer = mapper.map(customerDto, Customer.class);
         return customer;
     }

    @Override
    public List<CustomerDto> getAllCustomer(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customers.stream().map(customer -> mapToDto(customer)).collect(Collectors.toList()); // Use mapToDto method to convert Customer to CustomerDto
    }
    

    @Override
    public CustomerDto getCustomerById(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        return mapToDto(customer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto,long id ) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException
                ("Customer", "id", id));
        customer.setClient(customerDto.getClient());
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone_no(customerDto.getPhone_no());
        customer.setLast_modified_date(customerDto.getLast_modified_date());
        customer.setCustomer_code(customerDto.getCustomer_code());
        customer.setEnabled(customerDto.isEnabled());
        Customer updatedCustomer = customerRepository.save(customer);
        return mapToDto(updatedCustomer);
    }

    @Override
    public void deleteCustomer(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        customerRepository.deleteById(id);
    }
}
