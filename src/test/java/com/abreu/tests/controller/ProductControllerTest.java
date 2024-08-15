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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(productJson))
                    .andExpect(jsonPath("$.size()").value(PRODUCTS.size()))
                    .andExpect(jsonPath(ID_0).value(PRODUCTS.get(INDEX).getId()))
                    .andExpect(jsonPath(NAME_0).value(PRODUCTS.get(INDEX).getName()))
                    .andExpect(jsonPath(DESCRIPTION_0).value(PRODUCTS.get(INDEX).getDescription()))
                    .andExpect(jsonPath(EMAIL_0).value(PRODUCTS.get(INDEX).getEmail()));
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
                    .andExpect(jsonPath(ID).value(PRODUCT.getId()))
                    .andExpect(jsonPath(NAME).value(PRODUCT.getName()))
                    .andExpect(jsonPath(DESCRIPTION).value(PRODUCT.getDescription()))
                    .andExpect(jsonPath(EMAIL).value(PRODUCT.getEmail()));
        }
    }

}
