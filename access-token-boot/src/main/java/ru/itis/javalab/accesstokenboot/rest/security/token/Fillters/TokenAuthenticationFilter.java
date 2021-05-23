package ru.itis.javalab.accesstokenboot.rest.security.token.Fillters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.javalab.accesstokenboot.rest.models.Token;
import ru.itis.javalab.accesstokenboot.rest.security.token.TokenAuthentication;
import ru.itis.javalab.accesstokenboot.rest.services.JwtBlacklistService;
import ru.itis.javalab.accesstokenboot.rest.services.TokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 05.04.2021
 * 21. REST API
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
//    @Autowired
//    TokenService tokenService;
    @Autowired
    private JwtBlacklistService service;

    @Autowired
    private Algorithm algorithm;


    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException, AuthenticationException {
        String accessToken = request.getHeader("A-TOKEN");

        if (accessToken != null) {

            try {
                JWT.require(algorithm)
                        .build()
                        .verify(accessToken);
            } catch (JWTVerificationException e) {
                throw new BadCredentialsException("bad token");

            }

            if (service.exists(accessToken)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }


            TokenAuthentication tokenAuthentication = new TokenAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);

        }
        filterChain.doFilter(request, response);
    }
}
