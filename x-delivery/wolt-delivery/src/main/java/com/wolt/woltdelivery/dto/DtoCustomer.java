package com.wolt.woltdelivery.dto;


import com.wolt.woltdelivery.entity.Addresses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DtoCustomer {
    private long idCustomer;
    private String name;
    private String username;
    private String surname;
    private Boolean isDelete;
    private String password;
    private Float balance;
    private List<Addresses> addressesList = new ArrayList<>();


    public DtoCustomer(String name, String username, String surname, String password, Float balance, List<Addresses> addressesList) {
        this.name = name;
        this.username = username;
        this.surname = surname;
        this.password = password;
        this.balance = balance;
        this.addressesList = addressesList;
    }
}
