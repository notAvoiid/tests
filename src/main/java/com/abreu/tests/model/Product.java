package com.abreu.tests.model;

import com.abreu.tests.model.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String email;

    public Product(ProductDTO data) {
        this.id = data.id();
        this.name = data.name();
        this.description = data.description();
        this.email = data.email();
    }
}
