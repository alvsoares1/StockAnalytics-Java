package com.example.stockanalytics.services;

import com.example.stockanalytics.Repositories.ProductRepository;
import com.example.stockanalytics.dtos.ProductCreateDTO;
import com.example.stockanalytics.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ResponseEntity<Product> createProduct(ProductCreateDTO data) {
        Optional<Product> product = this.productRepository.findByName(data.name());

        if(product.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Product newProduct = new Product();
        newProduct.setName(data.name());
        newProduct.setPrice(data.price());
        newProduct.setQuantity(data.quantity());
        this.productRepository.save(newProduct);
        return ResponseEntity.ok(newProduct);
    }

    public ResponseEntity<Product> updateProduct(Long id, ProductCreateDTO data) {
        Optional<Product> product = this.productRepository.findById(id);

        if(product.isPresent()) {
            Product existingProduct = product.get();
            existingProduct.setName(data.name());
            existingProduct.setPrice(data.price());
            existingProduct.setQuantity(data.quantity());

            this.productRepository.save(existingProduct);
            return ResponseEntity.ok(existingProduct);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public boolean deleteProduct(Long id) {
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
