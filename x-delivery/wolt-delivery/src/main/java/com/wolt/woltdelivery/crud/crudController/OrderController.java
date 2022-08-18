package com.wolt.woltdelivery.crud.crudController;

import com.wolt.woltdelivery.crud.crudService.OrderService;
import com.wolt.woltdelivery.dto.DtoOrder;
import com.wolt.woltdelivery.entity.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("order")
public class OrderController {
    final OrderService orderService;


    @PostMapping
    public ResponseEntity<DtoOrder> save(@RequestBody Orders orders) {
        return orderService.saveOrder(orders);
    }

    @DeleteMapping("/{idOrders}")
    public ResponseEntity<String> cancel(@PathVariable long idOrders) {
        return orderService.orderCancel(idOrders);
    }

    @PostMapping("/withManyToMany")
    public ResponseEntity<String> saveWithManyToMany(@RequestBody Orders orders) {
        return orderService.saveOrderAndProductWithManyToMany(orders);
    }

    @PostMapping("/finish/{idOrders}")
    public ResponseEntity<String> finishOrder(@PathVariable long idOrders) {
        return orderService.orderFinish(idOrders);
    }


}
