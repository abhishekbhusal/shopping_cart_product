package com.technova.product_cart.Product.Cart.controller;

import com.technova.product_cart.Product.Cart.dto.ProductRequest;
import com.technova.product_cart.Product.Cart.model.Product;
import com.technova.product_cart.Product.Cart.service.ProductService;
import com.technova.product_cart.Product.Cart.utils.ApiResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api1")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/STR")
    public String name() {
        return "Hello";
    }
    @GetMapping("/products")
    public ResponseEntity<Object> getAllProduct(@RequestParam(name = "prodName", required = false) String prodName) {
        if (prodName != null) {
            Optional<Product> product = productService.getProductByProdName(prodName);
            if (product.isPresent()) {

                return ApiResponse.generateResponse(HttpStatus.OK.value(),"Product with prodName:" + prodName + "fetched successfully", product, null);
            }
            return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(), "Product with id" + prodName + "not found in our database", null, "Product not found");

        } else {
            List<Product> products = productService.getAllProducts();
            if (products.size() > 0) {
                return ApiResponse.generateResponse(HttpStatus.OK.value(), products.size() + "products available", products, null);
            }
            return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(), "No products available", null, "Product not found");

        }
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getProductById (@PathVariable Long id){
        Optional<Product> existingProduct = productService.getProductById(id);
        if (existingProduct.isPresent()) {
            return ApiResponse.generateResponse(HttpStatus.OK.value(), "Product fetch successfully", existingProduct, null);
        }
        return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(), "Product with id" + id + "not found in our database", null, "Product not found");
    }
    @PostMapping("/products")
    public ResponseEntity<Object> createProduct (@RequestBody ProductRequest productRequest){
        Optional<Product> product = productService.getProductByProdName(productRequest.getProdName());
        if (product.isPresent()){
            return ApiResponse.generateResponse(HttpStatus.OK.value(), "Product already exist with same name "+productRequest.getProdName(),null,null);
        }


        Product savedProduct = productService.createProduct(productRequest);
        return ApiResponse.generateResponse(HttpStatus.OK.value(), "Product created successfully", savedProduct, null);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct (@PathVariable Long id, @RequestBody ProductRequest productRequest){
        Optional<Product> existingProduct = productService.getProductById(id);
        if (existingProduct.isPresent()) {
            productService.updateProduct(existingProduct.get(), productRequest);
            return ApiResponse.generateResponse(HttpStatus.OK.value(), "Product updated", existingProduct.get(), null);
        }
        return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(), "Sorry Product with id " + id + " not found",null, "product not found");
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct (@PathVariable Long id){
        Optional<Product> existingProduct = productService.getProductById(id);
        if (existingProduct.isPresent()) {
            productService.deleteProduct(id);
            return ApiResponse.generateResponse(HttpStatus.OK.value(), "Product deleted successfully with id" + id, null, null);
        }
        return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(), "Product with id" + id + "not found in our database", null, "Product not found");

    }


}
