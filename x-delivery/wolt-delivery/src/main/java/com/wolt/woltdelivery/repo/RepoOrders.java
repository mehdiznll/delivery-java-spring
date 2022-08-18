package com.wolt.woltdelivery.repo;

import com.wolt.woltdelivery.entity.Orders;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoOrders extends JpaRepository<Orders,Long> {
Orders findByIdOrders(long idOrders);

}
