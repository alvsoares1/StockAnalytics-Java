package com.example.stockanalytics.controllers;

import com.example.stockanalytics.dtos.ProductCreateDTO;
import com.example.stockanalytics.entities.Product;
import com.example.stockanalytics.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody ProductCreateDTO data){
        return this.productService.createProduct(data);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductCreateDTO data) {
        return this.productService.updateProduct(id, data);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> listAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/check-stock/{id}")
    public ResponseEntity<String> checkStock(@PathVariable Long id) {
        return productService.checkProductStock(id);
    }
}
