package umg.edu.logisticayenvios.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private final Key key;
    private final long expirationMs;

    // Constructor actualizado: nombres más simples y compatibles
    public JwtService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expirationMs) {

        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationMs;
    }

    // Generar token con username y roles
    public String generarToken(String username, Collection<String> roles) {
        var ahora = new Date();
        var expiracion = new Date(ahora.getTime() + expirationMs);

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(ahora)
                .setExpiration(expiracion)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Validar token
    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Obtener username desde token
    public String obtenerUsernameDesdeToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // Obtener roles desde token
    @SuppressWarnings("unchecked")
    public List<String> obtenerRolesDesdeToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Object roles = claims.get("roles");
        if (roles instanceof Collection<?>) {
            return ((Collection<?>) roles)
                    .stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
