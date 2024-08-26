package com.architproject.productservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FakeStoreServiceRequestDTO {

    private String title;
    private Double price;
    private String description;
    private String category;
}
