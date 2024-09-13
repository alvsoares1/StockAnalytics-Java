    package com.example.stockanalytics.services;

    import com.example.stockanalytics.Repositories.ProductRepository;
    import com.example.stockanalytics.dtos.ProductCreateDTO;
    import com.example.stockanalytics.entities.Product;
    import com.example.stockanalytics.exceptions.ProductNotFoundException;
    import com.example.stockanalytics.exceptions.ProductAlreadyExistsException;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;

    @Service
    public class ProductService {
        @Autowired
        private ProductRepository productRepository;

        public Product createProduct(ProductCreateDTO data) {
            Optional<Product> existingProduct = productRepository.findByName(data.name());

            if (existingProduct.isPresent()) {
                throw new ProductAlreadyExistsException("Product with name " + data.name() + " already exists.");
            }

            Product newProduct = new Product();
            newProduct.setName(data.name());
            newProduct.setPrice(data.price());
            newProduct.setQuantity(data.quantity());
            return productRepository.save(newProduct);
        }

        public Product updateProduct(Long id, ProductCreateDTO data) {
            Product existingProduct = productRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found."));

            existingProduct.setName(data.name());
            existingProduct.setPrice(data.price());
            existingProduct.setQuantity(data.quantity());
            return productRepository.save(existingProduct);
        }

        public List<Product> getAllProducts() {
            return productRepository.findAll();
        }

        public List<Product> getProductsByPrice(double price) {
            return productRepository.getAllByPrice(price);
        }

        public List<Product> getProductsByName(String name) {
            return productRepository.getAllByName(name);
        }

        public List<Product> getProductsByQuantity(int quantity) {
            return productRepository.getAllByQuantity(quantity);
        }

        public void deleteProduct(Long id) {
            if (productRepository.existsById(id)) {
                productRepository.deleteById(id);
            } else {
                throw new ProductNotFoundException("Product with ID " + id + " not found.");
            }
        }

        public String checkProductStock(Long id) {
            Product existingProduct = productRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found."));

            if (existingProduct.getQuantity() < 10) {
                return "Precisa reabastecer o estoque";
            } else {
                return "Estoque OK";
            }
        }
    }
