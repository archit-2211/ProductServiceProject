package com.architproject.productservice.Controllers;


import com.architproject.productservice.DTOs.ControllerResponseDTO;
import com.architproject.productservice.DTOs.ProductDetailsRequestDTO;
import com.architproject.productservice.Exception.CreationUnsuccessfulException;
import com.architproject.productservice.Exception.ProductNotFoundException;
import com.architproject.productservice.Mappers.MapperClass;
import com.architproject.productservice.Models.Product;
import com.architproject.productservice.Services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping("/db/products")
public class ProductControllerforDB{

    ProductService productService;
    MapperClass mapper;

    //Constructor //
    public ProductControllerforDB(ProductService productService, MapperClass mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ControllerResponseDTO> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {

        Product product = productService.getProductById(id);
        ControllerResponseDTO responseDTO = mapper.productToDto(product);

        return ResponseEntity.ok(responseDTO);

    }

    @GetMapping("/this")
    public ResponseEntity<List<ControllerResponseDTO>> getAllProducts() throws ProductNotFoundException {
        List<ControllerResponseDTO> responseDTO = new ArrayList<>();

        for (Product product : productService.getAllProducts()) {
            responseDTO.add(mapper.productToDto(product));
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ControllerResponseDTO createProduct(@RequestBody ProductDetailsRequestDTO dto) throws CreationUnsuccessfulException {

        Product product = productService.addProduct(dto);

        return mapper.productToDto(product);

    }


    @PatchMapping("/{id}")
    public ControllerResponseDTO updateProductById(@PathVariable("id") Long id,@RequestBody ProductDetailsRequestDTO request) {


        Product product = productService.updateProduct(id,request);
        return mapper.productToDto(product);
    }

    @PutMapping("/{id}")
    public ControllerResponseDTO replaceProductById(@PathVariable("id") Long id, @RequestBody ProductDetailsRequestDTO request) {

        Product product = productService.replaceProduct(id,request);
        return mapper.productToDto(product);

    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteProductById(@PathVariable("id") Long id) {
        return productService.deleteProductById(id) ;
    }
}
