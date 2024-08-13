package com.abreu.tests.service;

import com.abreu.tests.model.Product;
import com.abreu.tests.model.dto.ProductDTO;
import com.abreu.tests.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found!"));
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public Product save(ProductDTO data) {
        findByEmail(data);
        return productRepository.save(new Product(data));
    }

    private void findByEmail(ProductDTO data) {
        var product = productRepository.findByEmail(data.email());
        if (product.isPresent() && !product.get().getId().equals(data.id())) throw new RuntimeException("Email already exists!");
    }
}
