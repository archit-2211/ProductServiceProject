package com.architproject.productservice.Services;

import com.architproject.productservice.DTOs.ProductDetailsRequestDTO;
import com.architproject.productservice.Exception.CreationUnsuccessfulException;
import com.architproject.productservice.Exception.ProductNotFoundException;
import com.architproject.productservice.Mappers.MapperClass;
import com.architproject.productservice.Models.Product;
import com.architproject.productservice.Repositories.CategoryRepository;
import com.architproject.productservice.Repositories.ProductRespository;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class RealProductService implements ProductService {
    private ProductRespository productRespository;
    private CategoryRepository CategoryRepository;
    private MapperClass mapperClass;

    public RealProductService(ProductRespository productRespository,
                              CategoryRepository CategoryRepository,
                              MapperClass mapperClass) {
        this.productRespository = productRespository;
        this.CategoryRepository = CategoryRepository;
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
    public Product updateProduct(Long id, ProductDetailsRequestDTO dto) {
        return null;
    }


    /*
    Have to implement the below Functionality
     */
    @Override
    public Product replaceProduct(Long id, ProductDetailsRequestDTO dto)  {
        Product optionalProduct = productRespository.save(mapperClass.detailDtoToProduct(dto));
        return optionalProduct;
    }

    @Override
    public HttpStatus deleteProductById(Long id) {
        return null;
    }
}
