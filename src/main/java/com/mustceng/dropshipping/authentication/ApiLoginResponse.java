package com.mustceng.dropshipping.authentication;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class ApiLoginResponse extends LoginResponse {

    private boolean admin;
}
