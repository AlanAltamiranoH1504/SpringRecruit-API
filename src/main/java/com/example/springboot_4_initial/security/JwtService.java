package com.example.springboot_4_initial.security;

import com.example.springboot_4_initial.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {
    @Value("${JWT_SECRET}")
    private String JWT_SECRET;

    // Generacion de token con claims
    public String generateTokenJWT(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles",
                user.getProfiles()
                        .stream()
                        .map(rol -> rol.getProfile())
                        .toList());

        return create_token(claims, user.getEmail(), user.getId_user());
    }

    // Generador de token
    public String create_token(Map<String, Object> claims, String email, Long idUser) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setId(String.valueOf(idUser))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10000 * 6000 * 30))
                .signWith(get_sign_key(), SignatureAlgorithm.HS512).compact();
    }

    // Firma del token
    protected Key get_sign_key() {
        byte[] key_bytes = Decoders.BASE64.decode(this.JWT_SECRET);
        return Keys.hmacShaKeyFor(key_bytes);
    }

    //Obtencion de todos los claims
    protected Claims extract_all_claims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(get_sign_key())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //Obtencion de claims de token
    protected <T> T extract_claim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extract_all_claims(token);
        return claimsResolver.apply(claims);
    }

    // Obtencion email del token
    protected String extract_email(String token) {
        return extract_claim(token, Claims::getSubject);
    }

    // Obtencion del idUser del token
    public Long extract_id_user(String token) {
        return Long.valueOf(extract_claim(token, Claims::getId));
    }

    // Obtener roles del toke
    protected List<String> extract_roles(String token) {
        return extract_claim(token, claims -> claims.get("roles", List.class));
    }
    // Obtencion fecha de expiracion de token
    protected Date extract_expiration(String token) {
        return extract_claim(token, Claims::getExpiration);
    }

    // Verificacion token expirado
    public boolean is_token_expired(String token) {
        return extract_expiration(token).before(new Date());
    }

    // Validacio de token
    public boolean is_token_valid(String token, User user) {
        final String email = extract_email(token);
        return (email.equals(user.getEmail())) && !is_token_expired(token);
    }
}
