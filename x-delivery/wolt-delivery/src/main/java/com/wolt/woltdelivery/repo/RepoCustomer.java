package com.wolt.woltdelivery.repo;

import com.wolt.woltdelivery.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoCustomer extends JpaRepository<Customer,Long> {

Customer findCustomerByIdCustomer(long idCustomer);
Customer findByName(String name);

@Query("select s from Customer s join fetch s.ordersList where s.idCustomer=:idCustomer")
Customer findCustomerByIdCustomer(@Param("idCustomer") Long idCustomer);




}
