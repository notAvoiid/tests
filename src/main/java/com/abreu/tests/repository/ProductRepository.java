package com.abreu.tests.repository;

import com.abreu.tests.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByEmail(String email);

}