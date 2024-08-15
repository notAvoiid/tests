package com.abreu.tests.utils;

import com.abreu.tests.model.Product;

import java.util.List;

public class ProductConstants {

    public static final Product PRODUCT = new Product(1L, "name", "description", "fulanodetal@gmail.com");
    public static final Product PRODUCT2 = new Product(2L, "name2", "description2", "fulanodetal2@gmail.com");
    public static final List<Product> PRODUCTS = List.of(PRODUCT, PRODUCT2);
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
    public static final String TYPE = "application/json";
    public static final String CONTENT_TYPE = "Content-Type";
}
