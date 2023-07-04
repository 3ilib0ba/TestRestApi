package com.evonniy.testapi.config.security;

import com.evonniy.testapi.config.security.MyBasicAuthenticationEntryPoint;
import com.evonniy.testapi.model.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.jaas.AbstractJaasAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AbstractJaasAuthenticationProvider jaasAuthenticationProvider;
    private final MyBasicAuthenticationEntryPoint basicAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling().authenticationEntryPoint(basicAuthenticationEntryPoint).and()
                .authenticationProvider(jaasAuthenticationProvider)
                .authorizeHttpRequests((authz) -> authz
                        .antMatchers(
                                "/documents/get_all",
                                "/documents/admin/sign_document"
                        )
                        .hasAnyAuthority(Role.ADMIN.name())

                        .antMatchers(
                                "/documents/get_my",
                                "/documents/create",
                                "/meetings/create"
                        )
                        .hasAnyAuthority(Role.ORGANIZATOR.name())

                        .antMatchers(
                                "/meetings/signUpForMeeting"
                        )
                        .hasAnyAuthority(Role.ORGANIZATOR.name(), Role.USER.name())

                        .antMatchers(
                                "/meetings/get_all",
                                "/**")
                        .permitAll()
                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        var source = new UrlBasedCorsConfigurationSource();
        var config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

}
