package com.example.stockanalytics.dtos;

import com.example.stockanalytics.entities.ProductType;

public record ProductCreateDTO(String name, Double price, int quantity, ProductType type) {
}
