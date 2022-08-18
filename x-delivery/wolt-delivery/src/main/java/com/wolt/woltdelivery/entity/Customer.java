package com.wolt.woltdelivery.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Where(clause = "is_delete=false")
@SQLDelete(sql = "update customer set is_delete=true where id_customer=?")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCustomer;
    private String name;
    private String surname;
    private String username;
    private String password;
    private Float balance;
    private Boolean isDelete;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer")
    private List<Addresses> addressesList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "customer")
    private List<Orders> ordersList = new ArrayList<>();


    @PrePersist
    public void softDelete() {
        this.isDelete = false;
    }

    public Customer(String name, String surname, String username, String password, Float balance, List<Addresses> addressesList) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.addressesList = addressesList;
    }
}
