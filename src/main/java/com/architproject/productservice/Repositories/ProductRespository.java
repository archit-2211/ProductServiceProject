package com.architproject.productservice.Repositories;


import com.architproject.productservice.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRespository extends JpaRepository<Product, Long> {


    Optional<Product> findById(long id);
    List<Product> findAll();
    Product save(Product product);







}
