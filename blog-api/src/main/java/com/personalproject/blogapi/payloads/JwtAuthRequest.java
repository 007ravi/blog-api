package com.personalproject.blogapi.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String username;

    private String password;
}
