package com.spring.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.product.entity.Product;
import com.spring.product.repository.ProductRepository;

@Service
public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    public Product addProduct(Product product){
        return repository.save(product);
    }

}
