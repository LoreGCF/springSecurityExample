
package com.example1.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
   /* @Bean
    public SecurityFilterChain filterchain(HttpSecurity httpSecurity) throws Exception{
    
        return httpSecurity
                .authorizeHttpRequests()
                   .requestMatchers("/v1/index2").permitAll()
                   .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .build();
    
    
    }
    */
    
    @Bean
    public SecurityFilterChain filterchain(HttpSecurity httpSecurity) throws Exception{
    
        return httpSecurity
                .authorizeHttpRequests(auth -> { 
                auth.requestMatchers("/v1/index2").permitAll();
                auth.anyRequest().authenticated();
                
                })
                .formLogin()
                    .successHandler(successHandler())// URL a donde se va a dirigir despues de iniciar sesion
                    .permitAll()
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                    .invalidSessionUrl("/login")
                    .maximumSessions(1)
                    .expiredUrl("/login")
                
                .and()
                .sessionFixation()
                    .migrateSession()
                
                .and()
                .build();
                
    
                }
    
    
    public AuthenticationSuccessHandler successHandler(){
    return((request, response, authentication) -> {
    response.sendRedirect("/v1/index");
        
    });
    
    }
    
    
}
