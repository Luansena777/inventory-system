package com.inventory_system.mapper;

import com.inventory_system.dto.ProductCreateDTO;
import com.inventory_system.dto.ProductDTO;
import com.inventory_system.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toDTO(Product p);

    Product toEntity(ProductCreateDTO dto);
}
