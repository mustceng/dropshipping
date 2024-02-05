package com.mustceng.dropshipping.authentication;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class JwtConfig {

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    // create token for 1 day;
    @Value("${security.jwt.expiration:#{24*60*60*1000}}")
    private long expiration;

    @Value("${security.jwt.secret:JwtSecretKey}")
    private String secret;
}
