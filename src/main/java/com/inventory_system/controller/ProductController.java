package com.inventory_system.controller;

import com.inventory_system.dto.ProductCreateDTO;
import com.inventory_system.dto.ProductDTO;
import com.inventory_system.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAllProducts() {
        List<ProductDTO> products = productService.findAllProducts();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductCreateDTO createProductDTO) {
        ProductDTO product = productService.createProduct(createProductDTO);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO updateProductDTO) {
        ProductDTO productDTO = productService.updateProduct(id, updateProductDTO);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
