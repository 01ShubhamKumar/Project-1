package com.project1.controller;

import com.project1.payload.CustomerDto;
import com.project1.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto){
        CustomerDto dto = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @GetMapping
    public List<CustomerDto>getAllCustomer( @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
            return customerService.getAllCustomer(pageable);
}
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") long id){
        CustomerDto Dto = customerService. getCustomerById(id);
        return new ResponseEntity<>(Dto,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto>updateCustomer(@RequestBody CustomerDto customerDto,@PathVariable("id") long id){
        CustomerDto updateDto= customerService.updateCustomer(customerDto,id);
        return new ResponseEntity<>(updateDto,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteCustomer(@PathVariable("id") long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>("Customer entity deleted !",HttpStatus.OK);
    }
}
