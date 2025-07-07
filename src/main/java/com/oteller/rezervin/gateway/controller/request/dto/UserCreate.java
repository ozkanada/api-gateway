package com.oteller.rezervin.gateway.controller.request.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreate(
        @NotBlank(message = "{pandora.constraint.username.notblank.message}")
        @Size(min = 4, max = 20,message = "{pandora.constraint.username.size.message}")
        String username,

        @NotBlank(message = "{pandora.constraint.email.notblank.message}")
        @Email(message = "{pandora.constraint.email.format.message}")
        String email,
        
        String firstName,
        
        String lastName,
        
        String activationToken,

        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",message = "{pandora.constraint.password.pattern.message}")
        String password
) {
}
