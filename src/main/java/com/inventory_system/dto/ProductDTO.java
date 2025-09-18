package com.inventory_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ProductDTO {
    private Long ID;

    @NotBlank(message = "O nome não pode estar vazio")
    private String name;

    private String description;

    @NotNull(message = "A quantidade não pode ser nula")
    @PositiveOrZero(message = "A quantidade deve ser um número positivo ou zero")
    private Integer quantity;
}
