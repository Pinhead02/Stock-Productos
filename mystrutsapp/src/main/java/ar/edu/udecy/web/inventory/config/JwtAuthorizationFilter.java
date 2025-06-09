package ar.edu.udecy.web.inventory.config;

import ar.edu.udecy.web.inventory.handler.exception.TokenInvalidException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String secretKeyString;

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.prefix}")
    private String prefix;

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(secretKeyString.getBytes());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = request.getHeader(header);

            if (token == null || !token.startsWith(prefix)) {
                chain.doFilter(request, response);
                return;
            }
        validateToken(token.replace(prefix, ""));
        chain.doFilter(request, response);
    }

    private void validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            if (username != null) {
                User authUser = new User(username, "", new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(authUser, null, authUser.getAuthorities()));
            }
        } catch (io.jsonwebtoken.MalformedJwtException e) {
            throw new TokenInvalidException("Invalid token: Malformed JWT");
        } catch (Exception e) {
            throw new TokenInvalidException("Invalid token: " + e.getMessage());
        }
    }
}