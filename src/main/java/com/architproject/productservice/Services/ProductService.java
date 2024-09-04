package com.architproject.productservice.Services;

import com.architproject.productservice.DTOs.ProductDetailsRequestDTO;
import com.architproject.productservice.Exception.CreationUnsuccessfulException;
import com.architproject.productservice.Exception.ProductNotFoundException;
import com.architproject.productservice.Models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ProductService {

    public Product getProductById(Long id) throws ProductNotFoundException;
    public List<Product> getAllProducts() throws ProductNotFoundException;

    public Product addProduct(ProductDetailsRequestDTO dto) throws CreationUnsuccessfulException;

    public Product replaceProduct(Long id, ProductDetailsRequestDTO dto);

    public HttpStatus deleteProductById(Long id);






}