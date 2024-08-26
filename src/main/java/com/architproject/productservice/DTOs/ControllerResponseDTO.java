package com.architproject.productservice.DTOs;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ControllerResponseDTO {

    private Long id ;
    private String name ;
    private String description ;
    private Double price ;
    private String category;
}
