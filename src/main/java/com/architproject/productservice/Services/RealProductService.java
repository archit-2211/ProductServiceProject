package com.architproject.productservice.Services;

import com.architproject.productservice.DTOs.ProductDetailsRequestDTO;
import com.architproject.productservice.Exception.CreationUnsuccessfulException;
import com.architproject.productservice.Exception.ProductNotFoundException;
import com.architproject.productservice.Mappers.MapperClass;
import com.architproject.productservice.Models.Product;
import com.architproject.productservice.Repositories.ProductRespository;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class RealProductService implements ProductService {
    private final ProductRespository productRespository;
    private final MapperClass mapperClass;

    public RealProductService(ProductRespository productRespository,
                              MapperClass mapperClass) {
        this.productRespository = productRespository;
        this.mapperClass = mapperClass;
    }


    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRespository.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product not found By specified Id " + id);
        }
        return product.get();
    }

    @Override
    public List<Product> getAllProducts() throws ProductNotFoundException {
        List<Product> products = productRespository.findAll();
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found");
        }
        return products;
    }

    @Override
    public Product addProduct(ProductDetailsRequestDTO dto) throws CreationUnsuccessfulException {
        Product optionalProduct = productRespository.save(mapperClass.detailDtoToProduct(dto));
        if (optionalProduct ==  null) {
            throw new CreationUnsuccessfulException("Something went wrong with database");
        }
        return optionalProduct;
    }


    @Override
    public Product replaceProduct(Long id, ProductDetailsRequestDTO dto)  {
        Product product = mapperClass.detailDtoToProduct(dto);
        product.setId(id);
        product = productRespository.save(product);
        if (product ==  null) {
            throw new RuntimeException("Replacing Product Unsuccessful");
        }
        return product;
    }

    @Override
    public HttpStatus deleteProductById(Long id) {
        productRespository.deleteById(id);

        return HttpStatus.OK;
    }





}
