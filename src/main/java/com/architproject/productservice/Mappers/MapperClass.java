package com.architproject.productservice.Mappers;

import com.architproject.productservice.DTOs.ControllerResponseDTO;
import com.architproject.productservice.DTOs.FakeStoreServiceRequestDTO;
import com.architproject.productservice.DTOs.FakeStoreServiceResponseDTO;
import com.architproject.productservice.DTOs.ProductDetailsRequestDTO;
import com.architproject.productservice.Models.Category;
import com.architproject.productservice.Models.Product;
import com.architproject.productservice.Services.ProductService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MapperClass {


    public ControllerResponseDTO productToDto(Product product) {

        ControllerResponseDTO responseDTO = new ControllerResponseDTO();
        responseDTO.setId(product.getId());
        responseDTO.setName(product.getName());
        responseDTO.setPrice(product.getPrice());
        responseDTO.setDescription(product.getDescription());
        responseDTO.setCategory(product.getCategory().getName());

        return responseDTO;
    }

    public Product dtoToProduct(FakeStoreServiceResponseDTO responseDTO) {

        Product product = new Product();
        product.setId(responseDTO.getId());
        product.setName(responseDTO.getTitle());
        product.setPrice(Double.parseDouble(responseDTO.getPrice()));
        product.setDescription(responseDTO.getDescription());
        Category category = new Category();
        category.setName(responseDTO.getCategory());
        product.setCategory(category);

        return product;

    }

    public FakeStoreServiceRequestDTO inputToRequestDTO (ProductDetailsRequestDTO request){
        FakeStoreServiceRequestDTO requestDTO = new FakeStoreServiceRequestDTO();
        requestDTO.setCategory(request.getCategory());
        requestDTO.setDescription(request.getDescription());
        requestDTO.setPrice(Double.parseDouble(request.getPrice()));
        requestDTO.setTitle(request.getName());

        return requestDTO;

    }

    public Product detailDtoToProduct(ProductDetailsRequestDTO responseDTO) {
        Product product = new Product();
        Date currDate = new Date() ;
        product.setCreatedAt( currDate );
        product.setDescription(responseDTO.getDescription());
        product.setPrice(Double.parseDouble(responseDTO.getPrice()));
        product.setName(responseDTO.getName());
        Category category = new Category();
        category.setName(responseDTO.getCategory());
        category.setCreatedAt( currDate );
        product.setCategory(category);

        return product;
    }


}
