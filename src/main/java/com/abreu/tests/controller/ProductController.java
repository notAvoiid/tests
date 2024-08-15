package com.abreu.tests.controller;

import com.abreu.tests.model.Product;
import com.abreu.tests.model.dto.ProductDTO;
import com.abreu.tests.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@Tag(name = "Products", description = "Endpoint for managing Products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(summary = "Find all Products!", description = "Find all Products!",
            tags = {"Products"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Product.class)))
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(operationId = "id", summary = "Find a Product!", description = "Find a Product by passing its ID!",
            tags = {"Products"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content (schema = @Schema(implementation = Product.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PutMapping
    @Operation(summary = "Updates a Product!",
            description = "Updates a Product by passing in a JSON his ID!",
            tags = {"Products"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content (schema = @Schema(implementation = ProductDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<Product> update(@RequestBody ProductDTO data) {
        return ResponseEntity.ok(productService.update(data));
    }

    @PostMapping
    @Operation(summary = "Adds a new Product!",
            description = "Adds a new Product by passing in a JSON representation of the Product!",
            tags = {"Products"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content (schema = @Schema(implementation = ProductDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<Product> save(@RequestBody ProductDTO data) {
        Product savedProduct = productService.save(data);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedProduct.getId()).toUri();
        return ResponseEntity.created(uri).body(savedProduct);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a Product!",
            description = "Deletes a Product by passing its ID!",
            tags = {"Products"},
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
