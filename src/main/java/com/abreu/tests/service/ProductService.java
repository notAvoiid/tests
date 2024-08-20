package com.abreu.tests.service;

import com.abreu.tests.exceptions.NameAlreadyExistsException;
import com.abreu.tests.exceptions.ProductNotFoundException;
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
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public Product save(ProductDTO data) {
        findByName(data);
        Product product = new Product(data);
        return productRepository.save(product);
    }

    @Transactional
    public Product update(ProductDTO data) {
        findByName(data);
        findById(data.id());
        return productRepository.save(new Product(data));
    }

    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) throw new ProductNotFoundException("Product not found!");
        productRepository.deleteById(id);
    }

    private void findByName(ProductDTO data) {
        var product = productRepository.findByName(data.name());
        if (product.isPresent() && !product.get().getId().equals(data.id())) throw new NameAlreadyExistsException("Name already exists!");
    }
}
