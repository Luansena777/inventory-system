package com.inventory_system.service;

import com.inventory_system.dto.ProductCreateDTO;
import com.inventory_system.dto.ProductDTO;
import com.inventory_system.exception.DuplicateProductException;
import com.inventory_system.exception.ProductNotFoundException;
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
    private final ProductMapper productMapper;

    /**
     * Retorna a lista de todos os produtos cadastrados.
     *
     * @return Uma lista de ProductDTO.
     */
    public List<ProductDTO> findAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product p : products) {
            productDTOS.add(productMapper.toDTO(p));
        }
        return productDTOS;
    }

    /**
     * Cria um novo produto no sistema.
     *
     * @param createProductDTO objeto contendo os dados do novo produto.
     * @return o {@link ProductDTO} representando o produto criado.
     * @throws DuplicateProductException se já existir um produto com o mesmo nome e descrição.
     */
    @Transactional
    public ProductDTO createProduct(ProductCreateDTO createProductDTO) {
        if (productRepository.existsByNameAndDescription(createProductDTO.getName(), createProductDTO.getDescription())) {
            throw new DuplicateProductException("Não é possível criar o produto. Um produto com o mesmo nome e descrição já existe.");
        }
        Product product = productMapper.toEntity(createProductDTO);
        Product savedProduct = productRepository.save(product);

        return productMapper.toDTO(savedProduct);
    }

    /**
     * Atualiza os dados de um produto existente.
     *
     * @param id identificador do produto a ser atualizado.
     * @param updateProductDTO objeto contendo os novos dados do produto.
     * @return o {@link ProductDTO} representando o produto atualizado.
     * @throws ProductNotFoundException se não existir produto com o ID informado.
     */
    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO updateProductDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado com o id: " + updateProductDTO.getID()));

        existingProduct.setName(updateProductDTO.getName());
        existingProduct.setDescription(updateProductDTO.getDescription());
        existingProduct.setQuantity(updateProductDTO.getQuantity());

        productRepository.save(existingProduct);
        return productMapper.toDTO(existingProduct);
    }

    /**
     * Remove um produto existente do sistema.
     *
     * @param id identificador do produto a ser removido.
     * @throws ProductNotFoundException se não existir produto com o ID informado.
     */
    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Produto não encontrado com o id: " + id);
        }

        productRepository.deleteById(id);
    }
}
