package com.oteller.rezervin.gateway.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(@Email(message = "{pandora.constraint.email.format.message}") String email,
                          @NotBlank(message = "{pandora.constraint.email.notblank.password}") String password) {
}
