package com.proyectoJesus.springboot.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private InfoAdicionalToken infoAdicionalToken;

	//Configuración de los permisos.
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")
		.checkTokenAccess("isAuthenticated()");
	}

	//Detalles del Cliente por el que me conecto
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		clients.inMemory().withClient("angularapp") //Usuario
		.secret(passwordEncoder.encode("12345")) //Contraseña
		.scopes("read", "write") //Permisos de lectura y escritura
		.authorizedGrantTypes("password", "refresh_token") //Los authorizedGrantTypes de la app
		.accessTokenValiditySeconds(3600) //tiempo que dura el acceso con el token en segundos
		.refreshTokenValiditySeconds(3600);
	}

	//Se ocupa proceso de autentificacion y validar el token
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter()));
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore())
		.accessTokenConverter(accessTokenConverter());
	}

	//Libreria de Spring para crear y validar los token
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		return jwtAccessTokenConverter;
	}
	
	
}
