package com.spring.product.service;

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
        Cart cart=new Cart(productId,quantity);
        return cartRepository.save(cart);
    }
}
