package com.wolt.woltdelivery.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@Where(clause = "is_delete=false")
@SQLDelete(sql = "update product set is_delete=true where is_delete=?")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "idProduct")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProduct;
    private String name;
    private float price;
    private Boolean isDelete;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "productList")
    private List<Orders> ordersList =new ArrayList<>();




    @PrePersist
    public void softDelete(){
        this.isDelete=false;
    }



}
