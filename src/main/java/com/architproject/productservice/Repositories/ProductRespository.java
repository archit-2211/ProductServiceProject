package com.architproject.productservice.Repositories;


import com.architproject.productservice.Models.Product;
import com.architproject.productservice.Projections.NameAndCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRespository extends JpaRepository<Product, Long> {


    Optional<Product> findById(long id);
    List<Product> findAll();
    Product save(Product product);

    void deleteById(Long id);

    @Query(value = "select p.name as product, c.name as category from Product p JOIN Category c ON p.category.id = c.id where p.id = :id")
    public List<NameAndCategory> getNameById(@Param("id") Long id);


}
