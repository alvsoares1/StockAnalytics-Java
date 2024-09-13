package com.example.stockanalytics.Repositories;

import com.example.stockanalytics.entities.Product;
import com.example.stockanalytics.entities.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    List<Product> getAllByPrice(Double price);
    List<Product> getAllByName(String name);
    List<Product> getAllByQuantity(int quantity);
    List<Product> getAllByProductType(ProductType type);;
}
