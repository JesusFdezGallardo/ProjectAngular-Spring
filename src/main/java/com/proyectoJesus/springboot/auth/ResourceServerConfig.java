package com.proyectoJesus.springboot.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	//Clase de configuración de los distintos permisos
	@Override
	public void configure(HttpSecurity http) throws Exception {
		//Rutas al controlador permitidas sin necesidad de validar
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/usuarios", "/api/asignaturas").permitAll()
		.anyRequest().authenticated()
		.and().cors();
		
//		configurationSource(corsConfigurationSource());
	}

//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration config = new CorsConfiguration();
//		//Añadimos dominio de servidor. Con * indicamos que puede ser cualquier origen 
//		config.setAllowedOrigins(Arrays.asList("https://proyectojesus-springboot.herokuapp.com")); //Nombre de dominio de Angular. * indica admite cualquier origen  ,"*"
//		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//		config.setAllowCredentials(true);
//		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", config);
//		return source;
//	}
//	
//	@Bean
//	public FilterRegistrationBean<CorsFilter> corsFilter(){
//		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>
//		(new CorsFilter(corsConfigurationSource()));
//		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//		return bean;
//	}
}
