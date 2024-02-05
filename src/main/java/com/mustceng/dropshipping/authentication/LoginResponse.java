package com.mustceng.dropshipping.authentication;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class LoginResponse {

    private Long userId;
    private String token;
    private String email;


}
