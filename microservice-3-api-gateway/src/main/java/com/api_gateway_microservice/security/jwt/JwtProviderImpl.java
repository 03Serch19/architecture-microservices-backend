package com.api_gateway_microservice.security.jwt;

import com.api_gateway_microservice.model.User;
import com.api_gateway_microservice.security.UserMain;
import com.api_gateway_microservice.utils.SecurityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
@Component
public class JwtProviderImpl implements JwtProvider{
    @Value("${app.jwt.secret-key}")
    private String JWT_SECRET;
    @Value("${app.jwt.expiration-in-ms}")
    private Long JWT_EXPIRATION_IN_MS;

    @Override
    public String generateToken(UserMain auth) {
     String authorities=auth.getAuthorities().stream().map(GrantedAuthority::getAuthority)
             .collect(Collectors.joining(","));

        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .subject(auth.getUsername())
                .claim("roles", authorities)
                .claim("userId", auth.getId())
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
                .signWith(key)
                .compact();
    }
    @Override
    public String generateToken(User userRegi) {

        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .subject(userRegi.getUsername())
                .claim("roles", userRegi.getRole())
                .claim("userId", userRegi.getId())
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
                .signWith(key)
                .compact();

    }

    private Claims extractClaims(HttpServletRequest request) {
      String token= SecurityUtils.extractAuthTokenFrommRequest(request);
      if(token==null){
          return null;
      }
        SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    @Override
    public boolean isValid(HttpServletRequest request){
     Claims claims=extractClaims(request);
      if (claims==null){
          return false;
      }
      if(claims.getExpiration().before(new Date())){
          return false;
      }
      return true;
    }
    @Override
    public Authentication getAuthentication(HttpServletRequest request){
        Claims claims=extractClaims(request);
        if(claims==null){
            System.out.println("DEBUG JWT: Claims is null, returning null Authentication."); // Depuración
            return null;
        }
        String username=claims.getSubject();
        Long userId=claims.get("userId", Long.class);
        //es lo mismo que abajo
      //  Set<GrantedAuthority> authorities= Arrays.stream(claims.get("roles",String.class).toString().split(",")).map((s)->SecurityUtils.convertToAuthority(s)).collect(Collectors.toSet());
        String rolesString = claims.get("roles", String.class); // Captura la cadena de roles
        if (rolesString == null) {
            System.out.println("DEBUG JWT: 'roles' claim is null in token."); // Depuración
            rolesString = ""; // Evitar NullPointerException si 'roles' no existe
        }
        System.out.println("DEBUG JWT: Roles string from token: '" + rolesString + "'"); // Depuración


       Set<GrantedAuthority> authorities= Arrays.stream(claims.get("roles",String.class).split(",")).map(SecurityUtils::convertToAuthority).collect(Collectors.toSet());

       UserDetails userDetails =UserMain.builder()
               .username(username)
               .authorities(authorities)
               .id(userId)
               .build();

       if(userDetails==null){
           return null;
       }
       return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
   }


}
