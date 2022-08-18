package com.wolt.woltdelivery.search;

import com.wolt.woltdelivery.dto.DtoCustomer;
import com.wolt.woltdelivery.dto.DtoOrder;
import com.wolt.woltdelivery.entity.Customer;
import com.wolt.woltdelivery.entity.Orders;
import com.wolt.woltdelivery.entity.Product;
import com.wolt.woltdelivery.repo.RepoAddresses;
import com.wolt.woltdelivery.repo.RepoCustomer;
import com.wolt.woltdelivery.repo.RepoOrders;
import com.wolt.woltdelivery.repo.RepoProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    final RepoCustomer repoCustomer;
    final RepoAddresses repoAddresses;
    final EntityManager entityManager;
    final RepoProduct repoProduct;
    final RepoOrders repoOrders;

    public ResponseEntity<List<DtoCustomer>> findByNameOrSurnameOrUsername(DtoCustomer dtoCustomer) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> root = criteriaQuery.from(Customer.class);

        List<Predicate> predicateList = new ArrayList<>();


        if (dtoCustomer.getName() != null) {
            Predicate name = criteriaBuilder.equal(root.get("name"), dtoCustomer.getName());
            predicateList.add(name);
        }

        if (dtoCustomer.getSurname() != null) {
            Predicate surname = criteriaBuilder.equal(root.get("surname"), dtoCustomer.getSurname());
            predicateList.add(surname);
        }

        if (dtoCustomer.getUsername() != null) {
            Predicate username = criteriaBuilder.equal(root.get("username"), dtoCustomer.getUsername());
            predicateList.add(username);
        }

        criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
        List<Customer> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        List<DtoCustomer> dtoCustomerList = new ArrayList<>();

        for (Customer customer : resultList) {
            DtoCustomer dtoCustomer1 = new DtoCustomer(
                    customer.getName(),
                    customer.getSurname(),
                    customer.getUsername(),
                    customer.getPassword(),
                    customer.getBalance(),
                    customer.getAddressesList()
            );
            dtoCustomerList.add(dtoCustomer1);
        }


        return new ResponseEntity<>(dtoCustomerList, HttpStatus.OK);


    }


    public ResponseEntity<List<Product>> findAllWithPriceFilterMax(int pageNumber, int pageSize) {

        Sort price = Sort.by(Sort.Direction.DESC, "price");


        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, price);
        Page<Product> page = repoProduct.findAll(pageRequest);

        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);


    }


    public ResponseEntity<List<Product>> findAllWithPriceFilterMin(int pageNumber, int pageSize) {

        Sort price = Sort.by(Sort.Direction.ASC, "price");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, price);
        Page<Product> page = repoProduct.findAll(pageRequest);
        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);

    }

    public ResponseEntity<List<Product>> findAllProductByName(String name) {

        TypedQuery<Product> query = entityManager.createQuery(
                "select s from Product s where s.name=:name", Product.class);

        query.setParameter("name", name);
        List<Product> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resultList, HttpStatus.OK);

    }

    public ResponseEntity<List<Orders>> findAllOrdersByNameCustomer(String name) {
//        TypedQuery<Customer> query = entityManager.createQuery
//                ("select s from Customer s join fetch Orders where s.idCustomer=:idCustomer", Customer.class);
//        query.setParameter("idCustomer",idCustomer);
//        List<Customer> resultList = query.getResultList();
//        if (resultList.isEmpty()){
//            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(resultList,HttpStatus.OK);

        Customer byName = repoCustomer.findByName(name);
        if (byName != null) {
            List<Orders> ordersList = byName.getOrdersList();
            return new ResponseEntity<>(ordersList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


}
