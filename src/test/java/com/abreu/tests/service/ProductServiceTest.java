package com.abreu.tests.service;

import com.abreu.tests.model.Product;
import com.abreu.tests.model.dto.ProductDTO;
import com.abreu.tests.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    public static final String EMAIL = "fulanodetal@gmail.com";
    public static final String DESCRIPTION = "description";
    public static final String NAME = "name";
    public static final long ID = 1L;
    public static final int INDEX = 0;
    public static final String PRODUCT_NOT_FOUND = "Product not found!";

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    static Product product;
    static ProductDTO productDTO;
    static Optional<Product> optionalProduct;

    @Captor
    private ArgumentCaptor<Product> productArgumentCaptor;

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

            assertEquals(PRODUCT_NOT_FOUND, exception.getMessage());
        }
    }

    @Nested
    class FindAll {

        @Test
        @DisplayName("Should return with success all products")
        void shouldReturnWithSuccessAllProducts() {

            when(productRepository.findAll()).thenReturn(List.of(product));

            var response = productService.findAll();

            assertNotNull(response);

            assertEquals(1, response.size());
            assertEquals(Product.class, response.get(INDEX).getClass());

            assertEquals(ID, response.get(INDEX).getId());
            assertEquals(NAME, response.get(INDEX).getName());
            assertEquals(DESCRIPTION, response.get(INDEX).getDescription());
            assertEquals(EMAIL, response.get(INDEX).getEmail());

            verify(productRepository, times(1)).findAll();
        }
    }

    @Nested
    class Create {

        @Test
        @DisplayName("Should return success when email not exists")
        void shouldReturnSuccessWhenEmailNotExists() {

            when(productRepository.save(productArgumentCaptor.capture())).thenReturn(product);

            var response = productService.save(productDTO);

            assertNotNull(response);
            assertEquals(Product.class, response.getClass());
            assertEquals(ID, response.getId());
            assertEquals(NAME, response.getName());
            assertEquals(DESCRIPTION, response.getDescription());
            assertEquals(EMAIL, response.getEmail());

            Product productCaptured = productArgumentCaptor.getValue();

            assertNotNull(productCaptured);
            assertEquals(Product.class, productCaptured.getClass());
            assertEquals(ID, productCaptured.getId());
            assertEquals(NAME, productCaptured.getName());
            assertEquals(DESCRIPTION, productCaptured.getDescription());
            assertEquals(EMAIL, productCaptured.getEmail());
        }

        @Test
        @DisplayName("Should throw a RuntimeException when email exists")
        void shouldThrowARuntimeExceptionWhenEmailExists() {
            when(productRepository.findByEmail(EMAIL)).thenReturn(optionalProduct);
            optionalProduct.get().setId(2L);

            var exception = assertThrows(RuntimeException.class, () -> productService.save(productDTO));

            assertEquals("Email already exists!", exception.getMessage());
        }
    }

    @Nested
    class Update {

        @Test
        @DisplayName("Should return success when product exists")
        void shouldReturnSuccessWhenProductExists() {

            when(productRepository.findById(ID)).thenReturn(optionalProduct);
            when(productRepository.save(productArgumentCaptor.capture())).thenReturn(product);

            var response = productService.update(ID, productDTO);

            assertNotNull(response);
            assertEquals(Product.class, response.getClass());
            assertEquals(ID, response.getId());
            assertEquals(NAME, response.getName());
            assertEquals(DESCRIPTION, response.getDescription());
            assertEquals(EMAIL, response.getEmail());

            Product productCaptured = productArgumentCaptor.getValue();

            assertNotNull(productCaptured);
            assertEquals(Product.class, productCaptured.getClass());
            assertEquals(ID, productCaptured.getId());
            assertEquals(NAME, productCaptured.getName());
            assertEquals(DESCRIPTION, productCaptured.getDescription());
            assertEquals(EMAIL, productCaptured.getEmail());
        }

        @Test
        @DisplayName("Should throw RuntimeException when ID not exists")
        void shouldThrowRuntimeExceptionWhenIDNotExists() {

            when(productRepository.findById(ID)).thenReturn(Optional.empty());

            var exception = assertThrows(RuntimeException.class, () -> productService.update(ID, productDTO));

            assertEquals(PRODUCT_NOT_FOUND, exception.getMessage());
        }

    }

    private void startProduct() {
        product = new Product(ID, NAME, DESCRIPTION, EMAIL);
        productDTO = new ProductDTO(ID, NAME, DESCRIPTION, EMAIL);
        optionalProduct = Optional.of(product);
    }
}
