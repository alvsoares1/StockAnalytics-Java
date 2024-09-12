package com.example.stockanalytics.controllers;

import com.example.stockanalytics.dtos.ProductCreateDTO;
import com.example.stockanalytics.entities.Product;
import com.example.stockanalytics.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody ProductCreateDTO data) {
        return ResponseEntity.ok(productService.createProduct(data));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductCreateDTO data) {
        return ResponseEntity.ok(productService.updateProduct(id, data));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> listAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/listQuantity/{quantity}")
    public ResponseEntity<List<Product>> listAllProductsByQuantity(@PathVariable Integer quantity) {
        List<Product> products = productService.getProductsByQuantity(quantity);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/listPrice/{price}")
    public ResponseEntity<List<Product>> listAllProductsByPrice(@PathVariable Double price) {
        List<Product> products = productService.getProductsByPrice(price);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/listName/{name}")
    public ResponseEntity<List<Product>> listAllProductsByName(@PathVariable String name) {
        List<Product> products = productService.getProductsByName(name);
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check-stock/{id}")
    public ResponseEntity<String> checkStock(@PathVariable Long id) {
        return ResponseEntity.ok(productService.checkProductStock(id));
    }
}
