package com.technova.product_cart.Product.Cart.service;

import com.technova.product_cart.Product.Cart.dto.ProductRequest;
import com.technova.product_cart.Product.Cart.model.Product;
import com.technova.product_cart.Product.Cart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    public List<Product> getAllProduct;
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(ProductRequest productRequest) {
        Product newProduct = new Product();
        newProduct.setProdName(productRequest.getProdName());
        newProduct.setCategory(productRequest.getCategory());
        newProduct.setUnitPrice(productRequest.getUnitPrice());
        newProduct.setProdQuantity(productRequest.getProdQuantity());
        return productRepository.save(newProduct);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> getProductByProdName(String prodName) {
        return productRepository.findByProdName(prodName);
    }

    public Product updateProduct(Product product, ProductRequest productRequest) {

        product.setProdName(productRequest.getProdName());
        product.setCategory(productRequest.getCategory());
        product.setUnitPrice(productRequest.getUnitPrice());
        product.setProdQuantity(productRequest.getProdQuantity());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }






}
