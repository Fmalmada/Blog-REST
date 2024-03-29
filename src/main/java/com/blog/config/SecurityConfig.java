package com.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())

            .httpBasic(Customizer.withDefaults())
            .authorizeHttpRequests(authorize -> {authorize
                .requestMatchers("/h2-console/**").permitAll()   
                .requestMatchers(HttpMethod.GET, "/entradas/**").permitAll()         
				.anyRequest().authenticated();
            }
			)
        ;

        /*http.authorizeHttpRequests(authorize -> {authorize
                .requestMatchers("/h2-console/**").permitAll()            
				.anyRequest().authenticated();
            }
			);*/

            return http.build();
    }
    
}
