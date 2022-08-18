package com.wolt.woltdelivery.crud.crudService;

import com.wolt.woltdelivery.dto.DtoOrder;
import com.wolt.woltdelivery.entity.Customer;
import com.wolt.woltdelivery.entity.OrderStatus;
import com.wolt.woltdelivery.entity.Orders;
import com.wolt.woltdelivery.entity.Product;
import com.wolt.woltdelivery.exception.BadRequestException;
import com.wolt.woltdelivery.repo.RepoAddresses;
import com.wolt.woltdelivery.repo.RepoCustomer;
import com.wolt.woltdelivery.repo.RepoOrders;
import com.wolt.woltdelivery.repo.RepoProduct;
import javassist.tools.web.BadHttpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    final RepoAddresses repoAddresses;
    final RepoCustomer repoCustomer;
    final RepoOrders repoOrders;
    final RepoProduct repoProduct;


    public ResponseEntity<DtoOrder> saveOrder(Orders orders) {
        Orders saveOrder = repoOrders.save(orders);
        DtoOrder dtoOrder = new DtoOrder();
        dtoOrder.setIdOrders(saveOrder.getIdOrders());
        dtoOrder.setCreatedDate(saveOrder.getCreatedDate());
        dtoOrder.setFinishDate(saveOrder.getFinishDate());
        dtoOrder.setCustomer(saveOrder.getCustomer());
        dtoOrder.setOrderStatus(saveOrder.getOrderStatus());
        dtoOrder.setProductList(saveOrder.getProductList());

        return new ResponseEntity<>(dtoOrder, HttpStatus.OK);


    }

    public ResponseEntity<String> saveOrderAndProductWithManyToMany(Orders orders) {
        Orders orders1 = repoOrders.findById(orders.getIdOrders()).orElseThrow(() -> new BadRequestException("Order not found by this id"));


        List<Product> productList = orders.getProductList();
        for (Product product : productList) {
            orders1.addProduct(product);
        }
        repoOrders.save(orders1);
        return new ResponseEntity<>("save success with ManyToMany", HttpStatus.OK);


    }


//    public ResponseEntity<DtoOrder> saveOrder(Orders orders){
//
//        List<Product> productList = repoProduct.saveAll(orders.getProductList());
//
//        Orders save = repoOrders.save(orders);
//        for (Product product : productList) {
//            save.addProduct(product);
//        }
//        repoOrders.save(save);
//
//        DtoOrder dtoOrder = new DtoOrder();
//        dtoOrder.setIdOrders(save.getIdOrders());
//        dtoOrder.setCreatedDate(save.getCreatedDate());
//        dtoOrder.setFinishDate(save.getFinishDate());
//        dtoOrder.setCustomer(save.getCustomer());
//        dtoOrder.setOrderStatus(save.getOrderStatus());
//        dtoOrder.setProductList(save.getProductList());
//
//
//
//        return new ResponseEntity<>(dtoOrder, HttpStatus.OK);
//
//    }


    public ResponseEntity<String> orderCancel(long idOrders) {
        Orders findOrders = repoOrders.findById(idOrders).orElse(null);
        if (findOrders != null) {
            findOrders.setOrderStatus(OrderStatus.CANCELLED);
            repoOrders.save(findOrders);
            return new ResponseEntity<>("Order cancel success", HttpStatus.OK);

        }
        return new ResponseEntity<>("Order cancel failed", HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<String> orderFinish(long idOrders) {
        Orders findOrder = repoOrders.findByIdOrders(idOrders);
        Customer customer = findOrder.getCustomer();

        List<Product> allProduct = new ArrayList<>();

        List<Product> productList = findOrder.getProductList();
        for (Product product : productList) {
            Product productByName = repoProduct.findProductByName(product.getName());
            Float balance = customer.getBalance();

            customer.setBalance(balance - productByName.getPrice());
            allProduct.add(product);


        }
        repoProduct.saveAll(allProduct);
        repoCustomer.save(customer);

        if (findOrder != null) {
            findOrder.setOrderStatus(OrderStatus.FINISHED);
            findOrder.setFinishDate(LocalDate.now());
            repoOrders.save(findOrder);


            return new ResponseEntity<>("Order finish success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Order finish failed", HttpStatus.NOT_FOUND);


    }


}
