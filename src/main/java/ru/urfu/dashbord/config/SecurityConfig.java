package ru.urfu.dashbord.config;

import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.urfu.dashbord.jpa.repository.UsersRepository;

@KeycloakConfiguration
@RequiredArgsConstructor
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

  private final UsersRepository usersRepository;

  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
//    super.configure(http);
    http.authorizeRequests()
        .requestMatchers(new AntPathRequestMatcher("/"))
        .permitAll()
        .anyRequest()
        .authenticated();
    http.oauth2Login()
        .and()
        .logout()
        .addLogoutHandler(new KeycloakLogoutHandler())
        .logoutSuccessUrl("/");
  }

  /**
   * Registers the KeycloakAuthenticationProvider with the authentication manager.
   */
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(getKeycloakAuthenticationProvider());
  }

  /**
   * Defines the session authentication strategy.
   */
  @Bean
  @Override
  protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
    return new RegisterSessionAuthenticationStrategy(buildSessionRegistry());
  }

  @Bean
  protected SessionRegistry buildSessionRegistry() {
    return new SessionRegistryImpl();
  }

  private KeycloakAuthenticationProvider getKeycloakAuthenticationProvider() {
    KeycloakAuthenticationProvider authenticationProvider = keycloakAuthenticationProvider();
    GrantedAuthoritiesMapper mapper = new SimpleAuthorityMapper();
//    GrantedAuthoritiesMapper mapper = new NullAuthoritiesMapper();
//    mapper.mapAuthorities(userDetailsService.loadUserByUsername())
    authenticationProvider.setGrantedAuthoritiesMapper(mapper);
    return authenticationProvider;
  }

}
