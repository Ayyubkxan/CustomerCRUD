package com.example.customercrud.service;

import com.example.customercrud.dto.ApiResponse;
import com.example.customercrud.dto.CustomerDto;
import com.example.customercrud.entity.Customer;
import com.example.customercrud.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    public List<Customer> customerList(){
        return customerRepository.findAll();
    }


    public Customer getCustomerById(Integer id) {
        Optional<Customer> byId = customerRepository.findById(id);
        return byId.get();
    }

    public ApiResponse saveCustomer(CustomerDto customerDto){
        boolean existsByPhone = customerRepository.existsByPhone(customerDto.getPhone());
        if (existsByPhone){
            return new ApiResponse("Bunday raqamli Customer avvaldan mavjud",false);
        }
        Customer customer = new Customer();
        customer.setAddress(customerDto.getAddress());
        customer.setName(customerDto.getName());
        customer.setPhone(customerDto.getPhone());
        customerRepository.save(customer);
        return new ApiResponse("Customer saqlandi",true);
    }

    public String editCustomer(CustomerDto customerDto,Integer id) {
        Optional<Customer> customerRepositoryById = customerRepository.findById(id);
        if (customerRepositoryById.isPresent()) {
            Customer customer = customerRepositoryById.get();
            customer.setName(customerDto.getName());
            customer.setPhone(customerDto.getPhone());
            customer.setAddress(customerDto.getAddress());
            customerRepository.save(customer);

        }
        return "edited Customer";
    }

    public String deleteCustomer(Integer id){
        customerRepository.deleteById(id);
        return "Delete Customer";
    }




}
