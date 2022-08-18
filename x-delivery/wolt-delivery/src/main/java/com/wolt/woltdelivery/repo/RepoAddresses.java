package com.wolt.woltdelivery.repo;


import com.wolt.woltdelivery.entity.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoAddresses extends JpaRepository<Addresses,Long> {

Addresses findAllByIdAddress(long idAddress);

}
