package com.finances.Finances.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .authorizeRequests(authorizeConfig ->{
                    authorizeConfig.antMatchers("/swagger-ui/index.html/**").permitAll();
                    authorizeConfig.antMatchers("/webjars/springfox-swagger-ui/**").permitAll();
                    authorizeConfig.antMatchers("/swagger-ui/**").permitAll();
                    authorizeConfig.antMatchers("/swagger-ui.html").permitAll();
                    authorizeConfig.antMatchers("/swagger-resources/**").permitAll();
                    authorizeConfig.antMatchers("/v3/api-docs/**").permitAll();
                    authorizeConfig.anyRequest().authenticated();
                })
                .oauth2Login(Customizer.withDefaults())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .build();
    }
}
