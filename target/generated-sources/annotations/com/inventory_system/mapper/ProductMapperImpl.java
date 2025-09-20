package com.inventory_system.mapper;

import com.inventory_system.dto.ProductCreateDTO;
import com.inventory_system.dto.ProductDTO;
import com.inventory_system.model.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-20T17:44:33-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toDTO(Product p) {
        if ( p == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setID( p.getID() );
        productDTO.setName( p.getName() );
        productDTO.setDescription( p.getDescription() );
        productDTO.setQuantity( p.getQuantity() );

        return productDTO;
    }

    @Override
    public Product toEntity(ProductCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( dto.getName() );
        product.setQuantity( dto.getQuantity() );
        product.setDescription( dto.getDescription() );

        return product;
    }
}
