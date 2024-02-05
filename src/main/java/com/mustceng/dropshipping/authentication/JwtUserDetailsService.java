package com.mustceng.dropshipping.authentication;

import com.mustceng.dropshipping.entity.user.User;
import com.mustceng.dropshipping.exception.ApiException;
import com.mustceng.dropshipping.exception.ResponseCode;
import com.mustceng.dropshipping.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("jwtUserDetailsService")
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Cacheable(value = "LOGIN_USER", key = "#username")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username.isEmpty())
            throw new InternalAuthenticationServiceException("Username not provided", new ApiException(ResponseCode.DATA_NOT_PROVIDED, "Kullanıcı adı"));

        User user = userRepository.findByUsername(username).orElseThrow(()-> new ApiException(ResponseCode.DATA_NOT_FOUND,"User"));

        if (user.getId()!=null){
            user=userRepository.findByIdAndActive(user.getId(),Boolean.TRUE).orElse(null);
            return this.createJwtUserDetails(user);
        }else{
            throw new InternalAuthenticationServiceException("User not found", new ApiException(ResponseCode.BAD_CREDENTIALS, "user"));
        }

    }

    @Cacheable(value = "LOGIN_USER", key = "#id")
    public UserDetails loadUserById(Long id) {

        User user = userRepository.findByIdAndActive(id, Boolean.TRUE).orElse(null);

        if (user == null)
            throw new InternalAuthenticationServiceException("User not found", new ApiException(ResponseCode.BAD_CREDENTIALS, "user"));

        return this.createJwtUserDetails(user);
    }

    private JwtUserDetails createJwtUserDetails(User user) {

        JwtUserDetails jwtUserDetails = new JwtUserDetails();

        jwtUserDetails.setPassword(user.getPassword());
        jwtUserDetails.setUsername(user.getUsername());
        jwtUserDetails.setAccountNonExpired(user.isAccountNonExpired());
        jwtUserDetails.setAccountNonLocked(user.isAccountNonLocked());
        jwtUserDetails.setCredentialsNonExpired(user.isCredentialsNonExpired());
        jwtUserDetails.setActive(user.getActive());

        jwtUserDetails.setId(user.getId());
        jwtUserDetails.setAdmin(user.isAdmin());
        jwtUserDetails.setForcePasswordChange(user.isForcePasswordChange());
        jwtUserDetails.setPasswordChangeDate(user.getPasswordChangeDate());
        jwtUserDetails.setLastPasswordResetDate(user.getLastPasswordResetDate());


        return jwtUserDetails;

    }

    public JwtUserDetails getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object o = authentication.getPrincipal();
            if (o instanceof JwtUserDetails) {
                return (JwtUserDetails) o;
            }
        }
        return null;
    }

}
