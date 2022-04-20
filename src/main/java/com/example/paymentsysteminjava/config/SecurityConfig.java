package com.example.paymentsysteminjava.config;

import com.example.paymentsysteminjava.filter.JwtFilterProvider;
import com.example.paymentsysteminjava.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final JwtFilterProvider filterProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .addFilterBefore(filterProvider, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/swagger-resources/**",
                        "/v2/api-docs**", "/webjars/**",
                        "/swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/agent/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/user/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(passwordEncoder);
    }
}
