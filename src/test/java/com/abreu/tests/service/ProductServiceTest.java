package com.abreu.tests.service;

import com.abreu.tests.exceptions.EmailAlreadyExistsException;
import com.abreu.tests.exceptions.ProductNotFoundException;
import com.abreu.tests.model.Product;
import com.abreu.tests.repository.ProductRepository;
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

import static com.abreu.tests.utils.ProductConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Captor
    private ArgumentCaptor<Product> productArgumentCaptor;

    @Nested
    class FindAll {

        @Test
        @DisplayName("Should return with success all products")
        void shouldReturnWithSuccessAllProducts() {

            when(productRepository.findAll()).thenReturn(List.of(PRODUCT));

            var response = productService.findAll();

            assertNotNull(response);

            assertEquals(1, response.size());
            assertEquals(PRODUCT.getClass(), response.get(INDEX).getClass());
            assertEquals(PRODUCT.getId(), response.get(INDEX).getId());
            assertEquals(PRODUCT.getName(), response.get(INDEX).getName());
            assertEquals(PRODUCT.getDescription(), response.get(INDEX).getDescription());
            assertEquals(PRODUCT.getStock(), response.get(INDEX).getStock());

            verify(productRepository, times(1)).findAll();
        }
    }

    @Nested
    class FindById {

        @Test
        @DisplayName("Should return success when id is found")
        void shouldReturnSuccessWhenIdIsFound() {

            when(productRepository.findById(PRODUCT.getId())).thenReturn(OPTIONAL_PRODUCT);

            var response = productService.findById(PRODUCT.getId());

            assertNotNull(response);
            assertNotNull(response.getId());
            assertNotNull(response.getName());
            assertNotNull(response.getDescription());
            assertNotNull(response.getStock());

            assertEquals(PRODUCT.getClass(), response.getClass());
            assertEquals(PRODUCT.getId(), response.getId());
            assertEquals(PRODUCT.getName(), response.getName());
            assertEquals(PRODUCT.getDescription(), response.getDescription());
            assertEquals(PRODUCT.getStock(), response.getStock());

            verify(productRepository, times(1)).findById(PRODUCT.getId());
        }

        @Test
        @DisplayName("Should throw a RuntimeException when id is not found")
        void shouldThrowARuntimeExceptionWhenIdIsNotFound() {

            when(productRepository.findById(PRODUCT.getId())).thenReturn(Optional.empty());

            var exception = assertThrows(ProductNotFoundException.class, () -> productService.findById(PRODUCT.getId()));

            assertEquals(PRODUCT_NOT_FOUND, exception.getMessage());
        }
    }

    @Nested
    class Create {

        @Test
        @DisplayName("Should return success when email not exists")
        void shouldReturnSuccessWhenEmailNotExists() {

            when(productRepository.save(productArgumentCaptor.capture())).thenReturn(PRODUCT);

            var response = productService.save(PRODUCT_DTO);

            assertNotNull(response);
            assertEquals(PRODUCT.getClass(), response.getClass());
            assertEquals(PRODUCT.getId(), response.getId());
            assertEquals(PRODUCT.getName(), response.getName());
            assertEquals(PRODUCT.getDescription(), response.getDescription());
            assertEquals(PRODUCT.getStock(), response.getStock());

            Product productCaptured = productArgumentCaptor.getValue();

            assertNotNull(productCaptured);
            assertEquals(PRODUCT.getClass(), productCaptured.getClass());
            assertEquals(PRODUCT.getId(), productCaptured.getId());
            assertEquals(PRODUCT.getName(), productCaptured.getName());
            assertEquals(PRODUCT.getDescription(), productCaptured.getDescription());
            assertEquals(PRODUCT.getStock(), productCaptured.getStock());

            verify(productRepository, times(1)).save(any());
        }

        @Test
        @DisplayName("Should throw a RuntimeException when email exists")
        void shouldThrowARuntimeExceptionWhenEmailExists() {
            when(productRepository.findByName(PRODUCT.getName())).thenReturn(OPTIONAL_PRODUCT);
            OPTIONAL_PRODUCT.get().setId(2L);

            var exception = assertThrows(EmailAlreadyExistsException.class, () -> productService.save(PRODUCT_DTO));

            assertEquals("Name already exists!", exception.getMessage());
            OPTIONAL_PRODUCT.get().setId(1L);
        }
    }

    @Nested
    class Update {

        @Test
        @DisplayName("Should return success when product exists")
        void shouldReturnSuccessWhenProductExists() {

            when(productRepository.findById(PRODUCT.getId())).thenReturn(OPTIONAL_PRODUCT);
            when(productRepository.save(productArgumentCaptor.capture())).thenReturn(PRODUCT);

            var response = productService.update(PRODUCT_DTO);

            assertNotNull(response);
            assertEquals(Product.class, response.getClass());
            assertEquals(PRODUCT.getId(), response.getId());
            assertEquals(PRODUCT.getName(), response.getName());
            assertEquals(PRODUCT.getDescription(), response.getDescription());
            assertEquals(PRODUCT.getStock(), response.getStock());

            Product productCaptured = productArgumentCaptor.getValue();

            assertNotNull(productCaptured);
            assertEquals(PRODUCT.getClass(), productCaptured.getClass());
            assertEquals(PRODUCT.getId(), productCaptured.getId());
            assertEquals(PRODUCT.getName(), productCaptured.getName());
            assertEquals(PRODUCT.getDescription(), productCaptured.getDescription());
            assertEquals(PRODUCT.getStock(), productCaptured.getStock());

            verify(productRepository, times(1)).findById(anyLong());
            verify(productRepository, times(1)).save(any());
        }

        @Test
        @DisplayName("Should throw RuntimeException when ID not exists")
        void shouldThrowRuntimeExceptionWhenIDNotExists() {

            when(productRepository.findById(PRODUCT.getId())).thenReturn(Optional.empty());

            var exception = assertThrows(ProductNotFoundException.class, () -> productService.update(PRODUCT_DTO));

            assertEquals(PRODUCT_NOT_FOUND, exception.getMessage());
        }
    }

    @Nested
    class Delete {

        @Test
        @DisplayName("Should return success when ID exists")
        void shouldReturnSuccessWhenIDExists() {

            when(productRepository.existsById(PRODUCT.getId())).thenReturn(true);
            doNothing().when(productRepository).deleteById(PRODUCT.getId());

            productService.delete(PRODUCT.getId());

            verify(productRepository, times(1)).existsById(PRODUCT.getId());
            verify(productRepository, times(1)).deleteById(PRODUCT.getId());
        }

        @Test
        @DisplayName("Should throw RuntimeException when ID not exists")
        void shouldThrowRuntimeExceptionWhenIDNotExists() {

            when(productRepository.existsById(PRODUCT.getId())).thenReturn(false);

            var exception = assertThrows(ProductNotFoundException.class, () -> productService.delete(PRODUCT.getId()));

            assertEquals(PRODUCT_NOT_FOUND ,exception.getMessage());

            verify(productRepository, times(1)).existsById(PRODUCT.getId());
            verify(productRepository, times(0)).deleteById(PRODUCT.getId());
        }

    }


}
