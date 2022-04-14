package io.github.jinyoungyou.todo.jwt;

import io.github.jinyoungyou.todo.jwt.dto.JwtDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtManager {

    private static final String AUTHORITY_KEY = "auth";
    private static final String BEARER_TYPE = "bearer";

    // TODO : 나중에 property 로 빼야할까?
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24;
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 14;

    private static final String AUTHORITY_SPLITTER = ",";

    private final Key secret;

    public JwtManager(String secret) {
        this.secret = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public JwtDto createJwt(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(AUTHORITY_SPLITTER));

        long now = (new Date()).getTime();
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiresIn = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITY_KEY, authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(secret, SignatureAlgorithm.HS256)
                .compact();

        String refreshToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITY_KEY, authorities)
                .setExpiration(refreshTokenExpiresIn)
                .signWith(secret, SignatureAlgorithm.HS256)
                .compact();

        return JwtDto.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .expiresIn(accessTokenExpiresIn.getTime())
                .refreshToken(refreshToken)
                .build();
    }

    // TODO : Spring Security Authentication Architecture에 대해 상세히 봐야 함.
    public Authentication getAuthentication(String accessToken) {
        Claims claims = getClaims(accessToken);
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = Arrays.stream(claims.get(AUTHORITY_KEY).toString().split(AUTHORITY_SPLITTER))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UserDetails userDetails = new User(claims.getSubject(), "", simpleGrantedAuthorityList);

        return new UsernamePasswordAuthenticationToken(userDetails, "", simpleGrantedAuthorityList);
    }

    public Claims getClaims(String token) throws JwtException {
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(secret)
                .build();

        return jwtParser.parseClaimsJws(token).getBody();
    }
}
