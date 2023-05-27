package sopt.org.FourthSeminar.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sopt.org.FourthSeminar.exception.Error;
import sopt.org.FourthSeminar.exception.model.UnauthorizedException;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")   // application.yml 상의 경로
    private String jwtSecret;

    @PostConstruct  // 생성 시점에서 딱 한번만 호출되는 메소드로 지정 가능
    protected void init() {
        jwtSecret = Base64.getEncoder()
                .encodeToString(jwtSecret.getBytes(StandardCharsets.UTF_8));  // JWT 인코딩은 호출 시점에 딱 한번만 처리되어야 한다.
    }

    // JWT 토큰 발급
    public String issuedToken(String userId) {
        final Date now = new Date();

        // 클레임 생성 -> Payload : 등록된 클레임 중 전부를 담아야 하는 것 X (필요에 따라 선택적으로 담아주면 된다)
        final Claims claims = Jwts.claims()
                .setSubject("access_token")  // token 이름(고정된 값X, 어떤 값인지 구분하기 위함)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 120 * 60 *1000L));  // milliseconds 단위 (*1000 = 1초) => 만료시간 2시간으로 지정
                /*
                 Test, 개발 시에는 만료시간 길게 지정해도 상관 X
                 BUT 실제 서비스에서는 해커에 대한 노출 위험이 있어 보통 시간 단위로 설정
                 */

        // private claim 등록
        claims.put("userId", userId);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .signWith(getSigningKey())  // byte 값으로 키를 생성하는 메소드로 따로 뺌
                .compact();
    }

    private Key getSigningKey() {
        final byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // JWT 토큰 검증
    public boolean verifyToken(String token) {
        try {
            final Claims claims = getBody(token);
            return true;
        } catch (RuntimeException e) {  // 여기서 검증 실패 시 (예외를 잡은 경우) False를 반환하게 해서 로직 수행
            if (e instanceof ExpiredJwtException) {
                throw new UnauthorizedException(Error.TOKEN_TIME_EXPIRED_EXCEPTION, Error.TOKEN_TIME_EXPIRED_EXCEPTION.getMessage());
            }
            return false;
        }
    }

    private Claims getBody(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // JWT 토큰 내용 확인
    public String getJwtContents(String token) {
        final Claims claims = getBody(token);
        return (String) claims.get("userId");
    }


}
