package com.abreu.tests.service;

import com.abreu.tests.model.Product;
import com.abreu.tests.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    public static final String EMAIL = "fulanodetal@gmail.com";
    public static final String DESCRIPTION = "description";
    public static final String NAME = "name";
    public static final long ID = 1L;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    static Product product;
    static Optional<Product> optionalProduct;

    @BeforeEach
    void setUp() {
        startProduct();
    }

    @Nested
    class FindById {

        @Test
        @DisplayName("Should return success when id is found")
        void shouldReturnSuccessWhenIdIsFound() {

            when(productRepository.findById(ID)).thenReturn(optionalProduct);

            var response = productService.findById(ID);

            assertNotNull(response);
            assertNotNull(response.getId());
            assertNotNull(response.getName());
            assertNotNull(response.getDescription());
            assertNotNull(response.getEmail());

            assertEquals(Product.class, response.getClass());
            assertEquals(ID, response.getId());
            assertEquals(NAME, response.getName());
            assertEquals(DESCRIPTION, response.getDescription());
            assertEquals(EMAIL, response.getEmail());
        }

        @Test
        @DisplayName("Should throw a RuntimeException when id is not found")
        void shouldThrowARuntimeExceptionWhenIdIsNotFound() {

            when(productRepository.findById(ID)).thenReturn(Optional.empty());

            var exception = assertThrows(RuntimeException.class, () -> productService.findById(ID));

            assertEquals("Product not found!", exception.getMessage());
        }
    }

    private void startProduct() {
        product = new Product(ID, NAME, DESCRIPTION, EMAIL);
        optionalProduct = Optional.of(product);
    }
}
