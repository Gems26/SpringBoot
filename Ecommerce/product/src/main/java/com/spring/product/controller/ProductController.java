package com.spring.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.product.entity.Product;
import com.spring.product.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
     private final ProductService productService;

     public ProductController(ProductService productService) {
        this.productService = productService;
     }

     @GetMapping
     public List<Product> showAllProducts(){
        return productService.getAllProducts();
     }
     
     @PostMapping
     public Product addProduct(@RequestBody Product product){
         return productService.addProduct(product);
     }


}
