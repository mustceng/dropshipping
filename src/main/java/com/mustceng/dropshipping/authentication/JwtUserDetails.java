package com.mustceng.dropshipping.authentication;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
public class JwtUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    // spring security specific
    private Collection<GrantedAuthority> authorities = new ArrayList<>();
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean active;//enabled

    private Long id;
    private String email;
    private boolean admin;
    private boolean forcePasswordChange;
    private Date passwordChangeDate;
    private Date lastPasswordResetDate;


    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
