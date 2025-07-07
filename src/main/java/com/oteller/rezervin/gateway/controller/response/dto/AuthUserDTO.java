package com.oteller.rezervin.gateway.controller.response.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserDTO {

    private long userId;
    private String username;
    private String password;
    private String email;
    private boolean isActive;
}
