package com.project.carfleet.jwt;

import com.project.carfleet.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode("SECRETKEYquiEstSuperLongSaGrandJeTeLeDisMoiMonAmi");
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        System.out.println("Claims " + claims + "Subject " + subject);
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(UserDto user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());
        return createToken(claims, user.getCP());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        System.out.println("claims " + claims);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
    }

    public String extractCP(String token){ return extractClaim(token, Claims::getSubject); }

    public Date extractExpiration(String token){ return extractClaim(token, Claims::getExpiration); }

    private Boolean isTokenExpired(String token){ return extractExpiration(token).before(new Date()); }

    public Boolean validateToken(String token, UserPrincipal userPrincipal){
        final String CP = extractCP(token);
        System.out.println("CP extracted from token " + CP);
        System.out.println("data from userPrincipal " + userPrincipal.getCP());
        return (CP.equals(userPrincipal.getCP()) && !isTokenExpired(token));
    }
}
