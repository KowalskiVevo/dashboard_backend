//package ru.urfu.dashbord.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//  @Bean
//  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    http.cors().and().csrf().disable().authorizeRequests()
//        .antMatchers("asdasd").permitAll()
//        .anyRequest().authenticated()
//        .and().oauth2Login();
////    httpSecurity.authorizeRequests()
////        .requestMatchers()..permitAll()
////        .anyRequest().authenticated()
////        .and()
////        .oauth2Login();
//    return http.build();
//  }
//
//  @Bean
//  public ClientRegistrationRepository clientRepository() {
//
//    ClientRegistration keycloak = keycloakClientRegistration();
//    return new InMemoryClientRegistrationRepository(keycloak);
//  }
//
//  private ClientRegistration keycloakClientRegistration() {
//
//    return ClientRegistration.withRegistrationId("dashboard_realm")
//        .clientId("my-client")
//        .redirectUri("http://localhost:8081")
//        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//        .issuerUri("http://localhost:8080/realms/dashboard_realm")
//        .authorizationUri("http://localhost:8080/realms/dashboard_realm/protocol/openid-connect/auth")
//        .tokenUri("http://localhost:8080/realms/dashboard_realm/protocol/openid-connect/token")
//        .userInfoUri("http://localhost:8080/realms/dashboard_realm/protocol/openid-connect/userinfo")
//        .build();
//  }
//}