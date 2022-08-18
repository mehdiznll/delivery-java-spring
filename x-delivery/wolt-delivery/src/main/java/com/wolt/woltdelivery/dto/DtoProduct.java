package com.wolt.woltdelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DtoProduct {

    private long idProduct;
    private String name;
    private float price;
    private Boolean isDelete;
}
