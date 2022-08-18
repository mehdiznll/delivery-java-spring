package com.wolt.woltdelivery.dto;

import com.wolt.woltdelivery.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DtoAddress {

    private long idAddress;
    private String name;
    private String blockNumber;
    private String floorNumber;
    private String doorNumber;
    private Customer customer;










}
