package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.swagger.models.HttpMethod;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	 @Override
	 protected void configure(HttpSecurity httpSecurity) throws Exception {
	    httpSecurity
	            // we don't need CSRF because our token is invulnerable
	            .csrf().disable()


	.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()

	            // don't create session

	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	.and()          

	            .authorizeRequests()

	            // allow anonymous resource requests
	            .antMatchers(
	                    "/",
	                    "/v2/api-docs",           // swagger
	                    "/webjars/**",            // swagger-ui webjars
	                    "/swagger-resources/**",  // swagger-ui resources
	                    "/configuration/**",      // swagger configuration
	                    "/*.html",
	                    "/favicon.ico",
	                    "/**/*.html",
	                    "/**/*.css",
	                    "/**/*.js"
	            ).permitAll()
	            .antMatchers("/auth/**").permitAll()
	            .anyRequest().authenticated();

	    // Custom JWT based security filter
	    httpSecurity
	            .addFilterBefore(jwtRequestFilter, 
	UsernamePasswordAuthenticationFilter.class);

	    // disable page caching
	    httpSecurity.headers().cacheControl();
	}
}
