package br.com.gabryelfrk.aulaspringsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecutiryConfig {

	// Filtros (Filters)
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
			.authorizeHttpRequests(
					authorizeConfig -> {
			              authorizeConfig.antMatchers("/public").permitAll();
			              authorizeConfig.antMatchers("/logout").permitAll();
						// Qualquer requisição tem que ser feita por algm autenticado (Convensão)
						authorizeConfig.anyRequest().authenticated();
			})
			//.formLogin(Customizer.withDefaults())
			.oauth2Login(Customizer.withDefaults())
			.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
			.build();
	}
}
