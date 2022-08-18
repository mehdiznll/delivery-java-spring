package com.wolt.woltdelivery.search;

import com.wolt.woltdelivery.dto.DtoCustomer;
import com.wolt.woltdelivery.dto.DtoOrder;
import com.wolt.woltdelivery.entity.Orders;
import com.wolt.woltdelivery.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class SearchController {
    final SearchService searchService;

    @GetMapping("/withCriteria")
    public ResponseEntity<List<DtoCustomer>> getCustomerByCriteria(DtoCustomer dtoCustomer) {
        return searchService.findByNameOrSurnameOrUsername(dtoCustomer);
    }


    @GetMapping("/product/max")
    public ResponseEntity<List<Product>> getProductWithPriceFilterMax(@RequestParam int pageNumber,
                                                                      @RequestParam int pageSize) {
        return searchService.findAllWithPriceFilterMax(pageNumber, pageSize);
    }

    @GetMapping("/product/min")
    public ResponseEntity<List<Product>> getProductWithPriceFilterMin(@RequestParam int pageNumber,
                                                                      @RequestParam int pageSize) {
        return searchService.findAllWithPriceFilterMin(pageNumber, pageSize);
    }


    @GetMapping("/product/{name}")
    public ResponseEntity<List<Product>> getAllByName(@PathVariable String name) {
        return searchService.findAllProductByName(name);
    }

    @GetMapping("/getOrdersByCustomerName/{name}")
    public ResponseEntity<List<Orders>> getOrdersByCustomerNameP(@PathVariable String name) {
        return searchService.findAllOrdersByNameCustomer(name);
        // solve N+1 problem
    }


}
