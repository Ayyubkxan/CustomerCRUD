package com.example.customercrud.controller;

import com.example.customercrud.dto.ApiResponse;
import com.example.customercrud.dto.CustomerDto;
import com.example.customercrud.entity.Customer;
import com.example.customercrud.repository.CustomerRepository;
import com.example.customercrud.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;



    @GetMapping
    public List<Customer> getCustomerList(){
        List<Customer> customers = customerService.customerList();
        return customers;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable Integer id){
        Customer customerById = customerService.getCustomerById(id);
        return customerById;
    }

    @PostMapping
    public ApiResponse addCustomer(@RequestBody CustomerDto customerDto){
        ApiResponse apiResponse = customerService.saveCustomer(customerDto);
        return apiResponse;
    }

    @PutMapping("/{id}")
    public String updateCustomer(@RequestBody CustomerDto customerDto,@PathVariable Integer id){
        String editCustomer = customerService.editCustomer(customerDto, id);
        return editCustomer;
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Integer id){
        String deleteCustomer = customerService.deleteCustomer(id);
        return deleteCustomer;
    }









}
