package com.abreu.tests.utils;

import com.abreu.tests.model.Product;
import com.abreu.tests.model.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public class ProductConstants {

    public static final Product PRODUCT = new Product(1L, "name", "description", "fulanodetal@gmail.com");
    public static final Product PRODUCT2 = new Product(2L, "name2", "description2", "fulanodetal2@gmail.com");
    public static final ProductDTO PRODUCT_DTO = new ProductDTO(1L, "name", "description", "fulanodetal@gmail.com");
    public static final List<Product> PRODUCTS = List.of(PRODUCT, PRODUCT2);
    public static final Optional<Product> OPTIONAL_PRODUCT = Optional.of(PRODUCT);
    public static final String URL = "/api/v1/product";
    public static final int INDEX = 0;
    public static final String ID_0 = "$[0].id";
    public static final String NAME_0 = "$[0].name";
    public static final String DESCRIPTION_0 = "$[0].description";
    public static final String EMAIL_0 = "$[0].email";
    public static final String ID = "$.id";
    public static final String NAME = "$.name";
    public static final String DESCRIPTION = "$.description";
    public static final String EMAIL = "$.email";
    public static final String APPLICATION_JSON_VALUE = "application/json";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String PRODUCT_NOT_FOUND = "Product not found!";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists!";
}
