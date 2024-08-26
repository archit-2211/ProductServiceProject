package com.architproject.productservice.DTOs;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetailsRequestDTO {
    
    private String name ;
    private String description ; 
    private String price ;
    private String category; 
}
