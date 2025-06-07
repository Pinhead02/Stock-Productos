package ar.edu.udecy.web.inventory.config;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
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
    private static final String SECRET_KEY_STRING = "mySuperSecretKeyWithAtLeast32Characters";
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = request.getHeader(HEADER);

        System.out.println("Validando token JWT: " + token);

        if (token == null || !token.startsWith(PREFIX)) {
            System.out.println("Token no presente o formato incorrecto");
            chain.doFilter(request, response);
            return;
        }

        try {
            token = token.replace(PREFIX, "");
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY) // Usa la misma clave dinámica
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            System.out.println("Token válido. Usuario autenticado: " + claims.getSubject());
            String username = claims.getSubject();
            if (username != null) {
                User authUser = new User(username, "", new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(authUser, null, authUser.getAuthorities()));
            }
        } catch (SignatureException e) {
            System.out.println("Error de firma del JWT: Clave incorrecta o token manipulado");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        } catch (Exception e) {
            System.out.println("Error general al procesar JWT");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the token");
            return;
        }

        chain.doFilter(request, response);
    }
}