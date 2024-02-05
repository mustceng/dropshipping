package com.mustceng.dropshipping.controller;

import com.mustceng.dropshipping.authentication.*;
import com.mustceng.dropshipping.entity.user.User;
import com.mustceng.dropshipping.exception.ApiException;
import com.mustceng.dropshipping.exception.ResponseCode;
import com.mustceng.dropshipping.response.ApiResponse;
import com.mustceng.dropshipping.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;
	private final UserService userService;
	private final JwtConfig jwtConfig;


	public AuthenticationController(AuthenticationManager authenticationManager, UserService userService, JwtConfig jwtConfig) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.jwtConfig = jwtConfig;
	}

	@PostMapping("/login")
	public ApiResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		try {
			User user = userService.getUserByUsername(loginRequest.getUsername());

			if (!user.getActive()) throw new ApiException(ResponseCode.DATA_NOT_FOUND, "Active user");

			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
			final String token = generateToken(authentication);

			ApiLoginResponse apiLoginResponse = ApiLoginResponse.builder()
					.admin(jwtUserDetails.isAdmin())
					.email(jwtUserDetails.getEmail())
					.token(token).build();

			return ApiResponse.of(apiLoginResponse);

		} catch (AuthenticationException e) {
			throw new ApiException(ResponseCode.BAD_CREDENTIALS, "user");
		}
	}

	public String generateToken(Authentication authentication) {
		JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtConfig.getExpiration());
		return Jwts.builder()
				.setSubject(Long.toString(jwtUserDetails.getId()))
				.setIssuedAt(new Date())
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
				.claim("username", jwtUserDetails.getUsername())
				.compact();
	}
}
