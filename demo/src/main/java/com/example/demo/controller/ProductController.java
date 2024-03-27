package com.example.demo.controller;

import com.example.demo.Entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/get/products/by/page/{pageNumber}/{search_key}")
    public ResponseEntity<List<Product>> getProductByPage(@RequestParam(defaultValue = "0") int pageNumber,
                                                          @RequestParam(defaultValue = "0") String search_key) {
        List<Product> products = productService.getProductByPage(pageNumber, search_key);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("header-name", "header-value");
        return ResponseEntity.ok().headers(httpHeaders).body(products);
    }

    @PreAuthorize("hasAnyRole('CUSTOMER''ADMIN')")
    @GetMapping("/get/product/{id}")
    public ResponseEntity<Product> get_product_by_id(@PathVariable("product_id") long product_id) {
        Product product = productService.product_by_id(product_id);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("header-name", "header-value");

        return ResponseEntity.ok().headers(httpHeaders).body(product);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @DeleteMapping("/delete/product/{id}")
    public ResponseEntity Delete_product(@PathVariable("product_id")long id) {
        productService.delete_product_by_id(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("delete-product", "delete-product-value");
        return ResponseEntity.ok().headers(httpHeaders).build();
    }



    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/get/product/details/{productDescription}")
    public ResponseEntity<List<Product>> getProductDetails(@PathVariable String productDescription) {
        productService.get_product_details(productDescription);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("header-name", "header-value");
        return ResponseEntity.ok().headers(httpHeaders).build();
    }

    @PreAuthorize("hasAnyRole('CUSTOMER''ADMIN')")
    @GetMapping("/get_product_less_than/{pageNumber}/{search_key}")
    public ResponseEntity<List<Product>> get_product_less_than (@PathVariable  int pageNumber,@PathVariable String search_key) {
        productService.find_by_price_less_than(pageNumber,search_key);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("product_name", "product_value");
        return ResponseEntity.ok().headers(httpHeaders).build();
    }


    @PreAuthorize("hasAnyRole('CUSTOMER''ADMIN')")
    @GetMapping("/get_product_more_than/{pageNumber}/{search_key}")
    public ResponseEntity<List<Product>> get_product_more_than (@PathVariable  int pageNumber,@PathVariable String search_key) {
        productService.find_by_price_more_than(pageNumber,search_key);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("product_name", "product_value");
        return ResponseEntity.ok().headers(httpHeaders).build();
    }

    @PreAuthorize("hasAnyRole('CUSTOMER''ADMIN')")
    @GetMapping("/getProductByName/{productName}")
    public ResponseEntity<Product> find_by_name(@PathVariable String productName) {
        productService.productName(productName);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("product", "value");
        return ResponseEntity.ok().headers(httpHeaders).build();
     }



    @PreAuthorize("hasAnyRole('CUSTOMER''ADMIN')")
     @GetMapping("find/all/products")
    public Optional<List<Product>> findAll() {
        return Optional.ofNullable(productService.findAll());
    }


    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/updateProduct/{id}/{productRequest}")
    public ResponseEntity<Product> productUpdate(@PathVariable long id,@RequestBody Product productRequest) {
        productService.updateProduct(id,productRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("product", "value");
        return ResponseEntity.ok().headers(httpHeaders).build();
    }


    @PreAuthorize("hasRole('CUSTOMER')")
    @PatchMapping("patch/")
    public ResponseEntity<Product> productPatch(@PathVariable long id, @RequestBody Map<String,Optional> map) {
        productService.updateFieldsOfUserById(id, map);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("product", "value");
        return ResponseEntity.ok().headers(httpHeaders).build();
    }

}
