package ru.itis.javalab.accesstokenboot.rest.security.token.Fillters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.javalab.accesstokenboot.rest.models.Token;
import ru.itis.javalab.accesstokenboot.rest.security.token.TokenAuthentication;
import ru.itis.javalab.accesstokenboot.rest.services.JwtBlacklistService;
import ru.itis.javalab.accesstokenboot.rest.services.TokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;

@Component
@Transactional
public class TokenRefreshFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    private Algorithm algorithm;
    @Autowired
    private JwtBlacklistService service;


    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String refreshToken = request.getHeader("R-TOKEN");


        if (SecurityContextHolder.getContext().getAuthentication() == null && refreshToken !=null) {

            if (service.exists(refreshToken)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }

//            if (tokenService.existToken(refreshToken, Token.TypeOfToken.REFRESH)) {
            String at = tokenService.createNewAccessTokenUsingRefrsh(refreshToken);
            TokenAuthentication tokenAuthentication = new TokenAuthentication(at);
            SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
            response.addHeader("NEW-ACCESS", at);

            //ToDo:можно обновлять и ref toke

//                }

    }


        filterChain.doFilter(request,response);
}

}
