package com.abreu.tests.controller;


import com.abreu.tests.model.dto.ProductDTO;
import com.abreu.tests.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.abreu.tests.utils.ProductConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @Nested
    class FindAll {

        @Test
        @DisplayName("Should return all products successfully")
        void shouldReturnAllProductsSuccessfully() throws Exception {
            when(productService.findAll()).thenReturn(PRODUCTS);

            String productJson = objectMapper.writeValueAsString(PRODUCTS);

            mockMvc.perform(get(URL)
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(content().json(productJson))
                .andExpect(content().contentType(APPLICATION_JSON))

                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))

                .andExpect(jsonPath("$.size()").value(PRODUCTS.size()))
                .andExpect(jsonPath(ID_0).value(PRODUCTS.get(INDEX).getId()))
                .andExpect(jsonPath(NAME_0).value(PRODUCTS.get(INDEX).getName()))
                .andExpect(jsonPath(DESCRIPTION_0).value(PRODUCTS.get(INDEX).getDescription()))
                .andExpect(jsonPath(STOCK_0).value(PRODUCTS.get(INDEX).getStock()));
        }
    }

    @Nested
    class FindById {

        @Test
        @DisplayName("Should Return Product By Id Successfully")
        void shouldReturnProductByIdSuccessfully() throws Exception {
            when(productService.findById(PRODUCT.getId())).thenReturn(PRODUCT);

            String productJson = objectMapper.writeValueAsString(PRODUCT);

            mockMvc.perform(get(URL + "/{id}", PRODUCT.getId())
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(content().json(productJson))
                .andExpect(content().contentType(APPLICATION_JSON))

                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))

                .andExpect(jsonPath(ID).value(PRODUCT.getId()))
                .andExpect(jsonPath(NAME).value(PRODUCT.getName()))
                .andExpect(jsonPath(DESCRIPTION).value(PRODUCT.getDescription()))
                .andExpect(jsonPath(STOCK).value(PRODUCT.getStock()));
        }
    }

    @Nested
    class Create {
        @Test
        @DisplayName("Should Create Product Successfully")
        void shouldCreateProductSuccessfully() throws Exception {
            when(productService.save(any(ProductDTO.class))).thenReturn(PRODUCT);

            String productJson = objectMapper.writeValueAsString(PRODUCT);

            mockMvc.perform(post(URL)
                            .contentType(APPLICATION_JSON)
                            .content(productJson))
                    .andExpect(status().isCreated())

                    .andExpect(header().exists("Location"))
                    .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))

                    .andExpect(jsonPath(ID).value(PRODUCT.getId()))
                    .andExpect(jsonPath(NAME).value(PRODUCT.getName()))
                    .andExpect(jsonPath(DESCRIPTION).value(PRODUCT.getDescription()))
                    .andExpect(jsonPath(STOCK).value(PRODUCT.getStock()));
        }
    }

    @Nested
    class Update {

        @Test
        @DisplayName("Should Update Product Successfully")
        void shouldUpdateProductSuccessfully() throws Exception {

            when(productService.update(any(ProductDTO.class))).thenReturn(PRODUCT);

            String productJson = objectMapper.writeValueAsString(PRODUCT);

            mockMvc.perform(put(URL)
                .contentType(APPLICATION_JSON)
                .content(productJson)
                .accept(APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))

                .andExpect(content().json(productJson))
                .andExpect(jsonPath(ID).value(PRODUCT.getId()))
                .andExpect(jsonPath(NAME).value(PRODUCT.getName()))
                .andExpect(jsonPath(DESCRIPTION).value(PRODUCT.getDescription()))
                .andExpect(jsonPath(STOCK).value(PRODUCT.getStock()));
        }

    }

    @Nested
    class Delete {

        @Test
        @DisplayName("Should Delete Product Successfully")
        void shouldDeleteProductSuccessfully() throws Exception {

            when(productService.findById(PRODUCT.getId())).thenReturn(PRODUCT);

            mockMvc.perform(delete(URL + "/{id}", PRODUCT.getId())
                .accept(APPLICATION_JSON))
                .andExpect(status().isNoContent());
        }
    }
}
