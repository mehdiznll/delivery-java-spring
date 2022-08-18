package com.wolt.woltdelivery.crud.crudService;

import com.wolt.woltdelivery.dto.DtoAddress;
import com.wolt.woltdelivery.dto.DtoCustomer;
import com.wolt.woltdelivery.entity.Addresses;
import com.wolt.woltdelivery.entity.Customer;
import com.wolt.woltdelivery.entity.Orders;
import com.wolt.woltdelivery.entity.Product;
import com.wolt.woltdelivery.repo.RepoAddresses;
import com.wolt.woltdelivery.repo.RepoCustomer;
import com.wolt.woltdelivery.repo.RepoOrders;
import com.wolt.woltdelivery.repo.RepoProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    final RepoAddresses repoAddresses;
    final RepoCustomer repoCustomer;
    final RepoOrders repoOrders;
    final RepoProduct repoProduct;



    public ResponseEntity<Customer> saveCustomer(DtoCustomer dtoCustomer){
        Customer customer = new Customer();
        customer.setName(dtoCustomer.getName());
        customer.setSurname(dtoCustomer.getSurname());
        customer.setUsername(dtoCustomer.getUsername());
        customer.setPassword(dtoCustomer.getPassword());
        customer.setBalance(dtoCustomer.getBalance());
        customer.setAddressesList(dtoCustomer.getAddressesList());

        repoAddresses.saveAll(customer.getAddressesList());
        Customer savedCustomer = repoCustomer.save(customer);

        return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
    }


    public ResponseEntity<Customer> updateCustomer(Customer customer){
        Customer customerByIdCustomer = repoCustomer.findCustomerByIdCustomer(customer.getIdCustomer());
        if (customerByIdCustomer==null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        Customer updateCustomer = repoCustomer.save(customer);
        return new ResponseEntity<>(updateCustomer,HttpStatus.OK);


    }




    public ResponseEntity<String> softDelete(long idCustomer){
        Customer customer = repoCustomer.findById(idCustomer).orElse(null);
        if (customer!=null){

            repoCustomer.deleteById(idCustomer);
            return new ResponseEntity<>("Customer delete success", HttpStatus.OK);
        }
            return new ResponseEntity<>("Customer deleted failed",HttpStatus.NOT_FOUND);
    }



    public ResponseEntity<Addresses> saveAddress(DtoAddress dtoAddress){
        Addresses addresses = new Addresses();
        addresses.setName(dtoAddress.getName());
        addresses.setBlockNumber(dtoAddress.getBlockNumber());
        addresses.setFloorNumber(dtoAddress.getFloorNumber());
        addresses.setDoorNumber(dtoAddress.getDoorNumber());
        addresses.setCustomer(dtoAddress.getCustomer());

        if (addresses.getCustomer()!=null) {
            repoCustomer.save(addresses.getCustomer());
            Addresses save1 = repoAddresses.save(addresses);
            return new ResponseEntity<>(save1,HttpStatus.OK);
        }else {
            Addresses save2 = repoAddresses.save(addresses);
            return new ResponseEntity<>(save2, HttpStatus.OK);
        }

    }














}
