package com.spring.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.product.entity.Cart;
import com.spring.product.entity.Product;
import com.spring.product.repository.CartRepository;
import com.spring.product.repository.ProductRepository;

@Service
public class CartService {

    private CartRepository cartRepository;
    private ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        super();
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart addToCart(Long productId,int quantity){
        Product product =  productRepository.findById(productId)
        .orElseThrow(()-> new RuntimeException("Product not found"));
        Cart cart=new Cart(product,quantity);
        return cartRepository.save(cart);
    }

    public List<Cart> getAllCarts(){
        return cartRepository.findAll();
    }
}
