package com.wolt.woltdelivery.dto;

import com.wolt.woltdelivery.entity.Customer;
import com.wolt.woltdelivery.entity.OrderStatus;
import com.wolt.woltdelivery.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DtoOrder {
    private long idOrders;
    private LocalDate createdDate;
    private LocalDate finishDate;
    private OrderStatus orderStatus;
    private List<Product> productList = new ArrayList<>();
    private Customer customer;





}
