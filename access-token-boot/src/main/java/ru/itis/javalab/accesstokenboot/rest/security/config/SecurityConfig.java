package ru.itis.javalab.accesstokenboot.rest.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import ru.itis.javalab.accesstokenboot.rest.security.token.*;
import ru.itis.javalab.accesstokenboot.rest.security.token.Fillters.JwtLogoutFilter;
import ru.itis.javalab.accesstokenboot.rest.security.token.Fillters.TokenAuthenticationFilter;
import ru.itis.javalab.accesstokenboot.rest.security.token.Fillters.TokenRefreshFilter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenAuthenticationFilter tokenAuthenticationFilter;

    @Autowired
    private TokenAuthenticationProvider tokenAuthenticationProvider;

    @Autowired
    private TokenRefreshFilter refreshFilter;

//    @Autowired
//    private TokenDeleteAuth tokenDeleteAuth;

    @Autowired
    private JwtLogoutFilter jwtLogoutFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().disable();
        http
                .addFilterAt(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(refreshFilter,TokenAuthenticationFilter.class)
//                .addFilterBefore(tokenDeleteAuth,TokenAuthenticationFilter.class)
                .addFilterAt(jwtLogoutFilter, LogoutFilter.class)
                .authorizeRequests()
                .antMatchers("/teachers").hasAuthority("ADMIN")
//                .antMatchers("/teachers").authenticated()
                .antMatchers("/login").permitAll()
                .antMatchers("/signIn").permitAll()
                .antMatchers("/logout").authenticated()
                .and()
                .sessionManagement().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(tokenAuthenticationProvider);
    }
}
