package com.wolt.woltdelivery.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "idOrders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idOrders;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDate createdDate;
    private LocalDate finishDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @PrePersist
    public void orderInProcess(){
        this.orderStatus=OrderStatus.IN_PROCESS;
    }


    @JoinTable(name = "ORDERS_PRODUCT",
                   joinColumns = @JoinColumn(name = "ID_ORDERS"),
            inverseJoinColumns = @JoinColumn(name = "ID_PRODUCT")

    )
    @ManyToMany
    private List<Product> productList = new ArrayList<>();

    public void addProduct(Product product){
    this.productList.add(product);
    }

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;


}
