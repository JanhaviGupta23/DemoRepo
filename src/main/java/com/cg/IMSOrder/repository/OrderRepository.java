package com.cg.IMSOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.IMSOrder.pojos.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer>{
}
