package com.wolt.woltdelivery.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Addresses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAddress;
    private String name;
    private String blockNumber;
    private String floorNumber;
    private String doorNumber;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;



}
