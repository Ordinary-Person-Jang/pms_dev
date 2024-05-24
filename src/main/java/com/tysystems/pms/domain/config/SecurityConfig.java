package com.tysystems.pms.domain.config;


import com.tysystems.pms.domain.filter.security.JwtAccessDeniedHandler;
import com.tysystems.pms.domain.filter.security.JwtAuthenticationFilter;
import com.tysystems.pms.domain.filter.security.JwtAuthorizationFilter;
import com.tysystems.pms.domain.repository.security.UsrRedisRepository;
import com.tysystems.pms.domain.repository.usr.UsrRepository;
import com.tysystems.pms.domain.service.security.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final CorsFilter corsFilter;

    private final UsrRepository usrRepository;

    private final LoginService loginService;
    private final UserDetailsService detailsService;
    private final UsrRedisRepository redisRepository;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtAccessDeniedHandler accessDeniedHandler;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MessageDigestPasswordEncoder("SHA-256");
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(detailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();

        security.csrf(AbstractHttpConfigurer::disable);
        security.sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(corsFilter)
                .formLogin(AbstractHttpConfigurer::disable)
                .addFilter(new JwtAuthenticationFilter(authenticationManager, redisRepository))
                .addFilter(new JwtAuthorizationFilter(authenticationManager, detailsService, redisRepository, loginService))
                .exceptionHandling(exceptionConfig ->
                        exceptionConfig.accessDeniedHandler(accessDeniedHandler))
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) -> {
                    authorizeRequests.requestMatchers("/api/bi/bim/pjtinfoReg/getPjtList").permitAll();
//                    authorizeRequests.requestMatchers("/api/**").hasAnyRole("OUT", "CUS");

                    authorizeRequests.requestMatchers("/api/**").hasAnyAuthority("OUT", "CUS");
                    authorizeRequests.anyRequest().permitAll();
                });

        return security.build();
    }

}
