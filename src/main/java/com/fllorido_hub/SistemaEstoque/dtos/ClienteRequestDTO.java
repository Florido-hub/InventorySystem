package com.fllorido_hub.SistemaEstoque.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteRequestDTO(
        @NotBlank(message = "name can't be empty")
        @Size(min = 3,max = 25, message = "name size must be less than or equal 25")
        String nome,

        @NotBlank (message = "name can't be empty")
        @Email(message = "email invalid")
        String email
){
}
