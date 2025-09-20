package com.inventory_system.service;

import com.inventory_system.dto.ProductCreateDTO;
import com.inventory_system.dto.ProductDTO;
import com.inventory_system.exception.DuplicateProductException;
import com.inventory_system.mapper.ProductMapper;
import com.inventory_system.model.Product;
import com.inventory_system.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    /**
     * Retorna a lista de todos os produtos cadastrados.
     *
     * @return Uma lista de ProductDTO.
     */
    public List<ProductDTO> findAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product p : products) {
            productDTOS.add(ProductMapper.INSTANCE.toDTO(p));
        }
        return productDTOS;
    }

    /**
     * Cria um novo produto no banco de dados.
     * Lança DuplicateProductException se um produto igual já existir.
     *
     * @param dto Objeto contendo os dados do novo produto.
     * @return O dto do produto salvo.
     */
    @Transactional
    public ProductDTO createProduct(ProductCreateDTO dto) {
        if (productRepository.existsByNameAndDescription(dto.getName(), dto.getDescription())) {
            throw new DuplicateProductException("Não é possível criar o produto. Um produto com o mesmo nome e descrição já existe.");
        }
        Product product = ProductMapper.INSTANCE.toEntity(dto);
        Product savedProduct = productRepository.save(product);

        return ProductMapper.INSTANCE.toDTO(savedProduct);
    }


}
