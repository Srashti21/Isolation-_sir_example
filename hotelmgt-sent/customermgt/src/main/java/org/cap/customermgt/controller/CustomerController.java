package org.cap.customermgt.controller;

import org.cap.customermgt.dto.CreateCustomerRequestDto;
import org.cap.customermgt.dto.CustomerDetailsDto;
import org.cap.customermgt.entities.Customer;
import org.cap.customermgt.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService service;

    @GetMapping
    public ResponseEntity<List<CustomerDetailsDto>> fetchAll() {
        List<Customer> list = service.fetchAll();
        List<CustomerDetailsDto>dtos=convertToDetails(list);
        ResponseEntity<List<CustomerDetailsDto>>response=new ResponseEntity<>(dtos,HttpStatus.OK);
        return response;
    }

    @PostMapping("/add")
    public ResponseEntity<CustomerDetailsDto> add(@RequestBody CreateCustomerRequestDto requestDto) {
        Customer customer = new Customer();
        customer.setName(requestDto.getCustomerName());
        customer = service.save(customer);
        CustomerDetailsDto dto = convertToDetailsDto(customer);
        ResponseEntity<CustomerDetailsDto> response = new ResponseEntity<>(dto, HttpStatus.OK);
        return response;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CustomerDetailsDto> add(@PathVariable("id") int id) {
        Customer customer = service.findById(id);
        CustomerDetailsDto dto = convertToDetailsDto(customer);
        ResponseEntity<CustomerDetailsDto> response = new ResponseEntity<>(dto, HttpStatus.OK);
        return response;
    }

    List<CustomerDetailsDto> convertToDetails(Collection<Customer> customers) {
        List<CustomerDetailsDto> dtos = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDetailsDto dto = convertToDetailsDto(customer);
            dtos.add(dto);
        }
        return dtos;
    }

    CustomerDetailsDto convertToDetailsDto(Customer customer) {
        CustomerDetailsDto dto = new CustomerDetailsDto();
        dto.setName(customer.getName());
        dto.setId(customer.getId());
        return dto;
    }
}
