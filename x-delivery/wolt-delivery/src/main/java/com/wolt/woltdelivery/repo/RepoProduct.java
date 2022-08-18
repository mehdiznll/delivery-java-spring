package com.wolt.woltdelivery.repo;

import com.wolt.woltdelivery.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface RepoProduct extends JpaRepository<Product,Long> {

Product findByIdProduct(long idProduct);
Product findProductByName(String name);





}
