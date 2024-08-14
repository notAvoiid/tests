package com.abreu.tests.controller;

import com.abreu.tests.model.Product;
import com.abreu.tests.model.dto.ProductDTO;
import com.abreu.tests.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    private static final long ID = 1L;
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String EMAIL = "fulanodetal@gmail.com";
    public static final int INDEX = 0;

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    static Product product;
    static ProductDTO productDTO;
    static Optional<Product> optionalProduct;

    @BeforeEach
    void setUp() {
        startProduct();
    }

    @Nested
    class FindAll {

        @Test
        @DisplayName("Should return all products when success")
        void shouldReturnAllProductsWhenSuccess(){
            when(productService.findAll()).thenReturn(List.of(product));

            var response = productController.findAll();

            assertNotNull(response);
            assertNotNull(response.getBody());

            assertEquals(HttpStatus.OK, response.getStatusCode());

            assertEquals(ResponseEntity.class, response.getClass());
            assertInstanceOf(List.class, response.getBody());
            assertEquals(Product.class, response.getBody().get(INDEX).getClass());
            assertEquals(ID, response.getBody().get(INDEX).getId());
            assertEquals(NAME, response.getBody().get(INDEX).getName());
            assertEquals(DESCRIPTION, response.getBody().get(INDEX).getDescription());
            assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());

            verify(productService, times(1)).findAll();
        }
    }

    @Nested
    class Create {
        @Test
        @DisplayName("Should return success when email does not exist")
        void shouldReturnSuccessWhenEmailDoesNotExist(){
            when(productService.save(any())).thenReturn(product);

            ResponseEntity<Product> response = productController.save(productDTO);

            assertNotNull(response);
            assertNotNull(response.getBody());

            assertEquals(HttpStatus.CREATED, response.getStatusCode());

            assertEquals(ResponseEntity.class, response.getClass());
            assertInstanceOf(Product.class, response.getBody());
            assertEquals(Product.class, response.getBody().getClass());
            assertEquals(ID, response.getBody().getId());
            assertEquals(NAME, response.getBody().getName());
            assertEquals(DESCRIPTION, response.getBody().getDescription());
            assertEquals(EMAIL, response.getBody().getEmail());

            verify(productService, times(1)).save(productDTO);
        }
    }


    private void startProduct() {
        product = new Product(ID, NAME, DESCRIPTION, EMAIL);
        productDTO = new ProductDTO(ID, NAME, DESCRIPTION, EMAIL);
        optionalProduct = Optional.of(product);
    }

}
