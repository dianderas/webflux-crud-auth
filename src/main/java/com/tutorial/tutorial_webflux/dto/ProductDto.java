package com.tutorial.tutorial_webflux.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {
    @NotBlank(message = "name is mandatory")
    public String name;
    @Min( value = 1, message = "price must be greater than 0")
    public float price;
}
