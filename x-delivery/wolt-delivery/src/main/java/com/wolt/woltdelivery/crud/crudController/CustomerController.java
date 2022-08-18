package com.wolt.woltdelivery.crud.crudController;

import com.wolt.woltdelivery.crud.crudService.CustomerService;
import com.wolt.woltdelivery.dto.DtoAddress;
import com.wolt.woltdelivery.dto.DtoCustomer;
import com.wolt.woltdelivery.entity.Addresses;
import com.wolt.woltdelivery.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    final CustomerService customerService;


    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody DtoCustomer dtoCustomer){
        return customerService.saveCustomer(dtoCustomer);
    }

    @PutMapping
    public ResponseEntity<Customer> update(@RequestBody Customer customer){
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/{idCustomer}")
    public ResponseEntity<String> softDelete(@PathVariable long idCustomer){
        return customerService.softDelete(idCustomer);
    }

    @PostMapping("/address")
    public ResponseEntity<Addresses> save(@RequestBody DtoAddress dtoAddress){
        return customerService.saveAddress(dtoAddress);
    }

























}
