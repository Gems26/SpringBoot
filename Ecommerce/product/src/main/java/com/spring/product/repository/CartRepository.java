package com.spring.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.product.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{

}
