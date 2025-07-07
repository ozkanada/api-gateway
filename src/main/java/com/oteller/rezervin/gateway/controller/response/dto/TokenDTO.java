package com.oteller.rezervin.gateway.controller.response.dto;

import com.oteller.rezervin.gateway.entity.User;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {

    private User user;
    private String accessToken;
    private String refreshToken;
}
