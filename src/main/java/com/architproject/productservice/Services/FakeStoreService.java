package com.architproject.productservice.Services;

import com.architproject.productservice.DTOs.FakeStoreServiceRequestDTO;
import com.architproject.productservice.DTOs.FakeStoreServiceResponseDTO;
import com.architproject.productservice.DTOs.ProductDetailsRequestDTO;
import com.architproject.productservice.Exception.CreationUnsuccessfulException;
import com.architproject.productservice.Exception.ProductNotFoundException;
import com.architproject.productservice.Models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.datasource.init.CannotReadScriptException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import com.architproject.productservice.Mappers.MapperClass;


import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreServiceObj")
public class FakeStoreService implements ProductService{

    private final WebClient webClient;
    private final MapperClass mapperClass;

    //Constructor//
    public FakeStoreService(WebClient webClient, MapperClass mapperClass) {
        this.webClient = webClient;
        this.mapperClass = mapperClass;
    }


    @Override
    public Product getProductById(Long id) throws ProductNotFoundException{

        Flux<FakeStoreServiceResponseDTO> reponseFlux = webClient.get().uri("https://fakestoreapi.com/products/"+id)
                .retrieve().bodyToFlux(FakeStoreServiceResponseDTO.class);

        FakeStoreServiceResponseDTO fakeStoreServiceResponseDTO = reponseFlux.blockFirst();
        if (fakeStoreServiceResponseDTO == null) {
            throw new ProductNotFoundException("Product Not Found By this id");
        }

        return mapperClass.dtoToProduct(fakeStoreServiceResponseDTO);
    }

    @Override
    public List<Product> getAllProducts() throws ProductNotFoundException{
        List<FakeStoreServiceResponseDTO> responses = webClient.get().uri("'https://fakestoreapi.com/products")
                .retrieve().bodyToFlux(FakeStoreServiceResponseDTO.class).collectList().block();

        if (responses == null || responses.isEmpty()) {
            throw new ProductNotFoundException("There are no products in DataBase");
        }

        List<Product> products = new ArrayList<>();
        for (FakeStoreServiceResponseDTO dto : responses) {
            products.add(mapperClass.dtoToProduct(dto));
        }

        return products;
    }

    @Override
    public Product addProduct(ProductDetailsRequestDTO dto) throws CreationUnsuccessfulException {
        FakeStoreServiceRequestDTO request = mapperClass.inputToRequestDTO(dto) ;

        Flux<FakeStoreServiceResponseDTO> response = webClient.post().uri("https://fakestoreapi.com/products").bodyValue(request).retrieve().bodyToFlux(FakeStoreServiceResponseDTO.class);
        FakeStoreServiceResponseDTO fakeStoreServiceResponseDTO = response.blockFirst();

        if (fakeStoreServiceResponseDTO == null) {
            throw new CreationUnsuccessfulException("Something Went Wrong");
        }
        return mapperClass.dtoToProduct(fakeStoreServiceResponseDTO);

    }

    @Override
    public Product updateProduct(Long id, ProductDetailsRequestDTO dto) {
        FakeStoreServiceRequestDTO request = mapperClass.inputToRequestDTO(dto);
        Flux<FakeStoreServiceResponseDTO> response = webClient.patch().uri("https://fakestoreapi.com/products/"+id).bodyValue(request).retrieve().bodyToFlux(FakeStoreServiceResponseDTO.class);
        FakeStoreServiceResponseDTO fakeStoreServiceResponseDTO = response.blockFirst();

        if (fakeStoreServiceResponseDTO == null) {
            throw new RuntimeException("Something Went Wrong");
        }
        return mapperClass.dtoToProduct(fakeStoreServiceResponseDTO);
    }

    @Override
    public Product replaceProduct(Long id, ProductDetailsRequestDTO dto) {
        FakeStoreServiceRequestDTO request = mapperClass.inputToRequestDTO(dto);
        Flux<FakeStoreServiceResponseDTO> response = webClient.put().uri("https://fakestoreapi.com/products/"+id).bodyValue(request).retrieve().bodyToFlux(FakeStoreServiceResponseDTO.class);
        FakeStoreServiceResponseDTO fakeStoreServiceResponseDTO = response.blockFirst();

        if (fakeStoreServiceResponseDTO == null) {
            throw new RuntimeException("Something Went Wrong");
        }
        return mapperClass.dtoToProduct(fakeStoreServiceResponseDTO);


    }

    @Override
    public HttpStatus deleteProductById(Long id) {

        FakeStoreServiceResponseDTO response = webClient.delete().uri("https://fakestoreapi.com/products/"+ id).retrieve().bodyToFlux(FakeStoreServiceResponseDTO.class).blockFirst();

        if (response == null) {
            return HttpStatus.NOT_FOUND;
        }

        return HttpStatus.OK;


    }
}
