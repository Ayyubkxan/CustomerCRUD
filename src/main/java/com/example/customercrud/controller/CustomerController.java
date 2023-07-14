package com.example.customercrud.controller;

import com.example.customercrud.dto.ApiResponse;
import com.example.customercrud.dto.CustomerDto;
import com.example.customercrud.entity.Customer;
import com.example.customercrud.service.CustomerService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ApiResponse addCustomer(@Valid @RequestBody CustomerDto customerDto){
        ApiResponse apiResponse = customerService.saveCustomer(customerDto);
        return apiResponse;
    }

    @PutMapping("/{id}")
    public String updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable Integer id){
        String editCustomer = customerService.editCustomer(customerDto, id);
        return editCustomer;
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Integer id){
        String deleteCustomer = customerService.deleteCustomer(id);
        return deleteCustomer;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity validationErrors(MethodArgumentNotValidException exception){
        List<String> errors = exception.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
                return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    private Map<String, List<String>> getErrorsMap(List<String> errors){
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("error",errors);
        return errorResponse;
    }

}
