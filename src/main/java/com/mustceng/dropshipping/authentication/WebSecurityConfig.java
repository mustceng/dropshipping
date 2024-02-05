package com.mustceng.dropshipping.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	private final JwtUserDetailsService jwtUserDetailsService = null;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().httpBasic().and().authorizeRequests()
				.antMatchers("/login").permitAll()
				.anyRequest()
				.authenticated();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
				.userDetailsService(jwtUserDetailsService)
				.passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}



	/*
	save user info in memory and password encode
	 */
	/*
		@Autowired
	public void configureAuthGlobal(AuthenticationManagerBuilder auth){
		try {
			auth.inMemoryAuthentication()
					.withUser("user").password(passwordEncoder().encode("password")).roles("USER")
					.and()
					.withUser("admin").password(passwordEncoder().encode("password")).roles( "ADMIN");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 */

}
