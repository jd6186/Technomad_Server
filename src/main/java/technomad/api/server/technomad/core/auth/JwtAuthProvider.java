package technomad.api.server.technomad.core.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import technomad.api.server.technomad.api.user.service.UserService;
import technomad.api.server.technomad.core.dto.auth.JwtUserDto;
import technomad.api.server.technomad.core.util.CommonUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JwtAuthProvider {
    private final UserService userService;

    @Autowired
    public JwtAuthProvider(UserService userService){
        this.userService = userService;
    }

    @Value("${jwt.secret}")
    private String JWT_SECRET_KEY;
    @Value("${jwt.accessTokenValidationSecond}")
    private String TOKEN_VALIDATION_SECOND;
    @Value("${jwt.refreshTokenValidationSecond}")
    private String REFRESH_TOKEN_VALIDATION_SECOND;

    /**
     * JWT 토큰 발급
     * @param userId - 유저 고유번호
     * @param userLoginId - 유저 Login 시 사용된 Account Id
     * @param tokenTime - token 유지 시간
     */
    private String createToken(String userId, String userLoginId, String tokenTime){
        Date now = new Date();
        Claims claim = Jwts.claims().setSubject(userLoginId);
        return Jwts.builder()
                .setClaims(claim)
                .setId(userId)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Long.parseLong(tokenTime)))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
                .compact();
    }

    /**
     * JWT 토큰 발급
     * @param userAccountId - 유저 Login 시 사용된 Account Id
     */
    public String createAccessToken(String userAccountId) {
        String userId = String.valueOf(userService.getUserDetailByAccountId(userAccountId).getUserId());
        return createToken(userId, userAccountId, TOKEN_VALIDATION_SECOND);
    }

    /**
     * JWT 토큰 발급
     * @param userAccountId - 유저 Login 시 사용된 Account Id
     */
    public String createRefreshToken(String userAccountId) {
        String userId = String.valueOf(userService.getUserDetailByAccountId(userAccountId).getUserId());
        return createToken(userId, userAccountId, REFRESH_TOKEN_VALIDATION_SECOND);
    }

    /**
     * Bearer를 제외한 순수 Access Token 확보
     * @param accessToken - Access Token
     */
    public String getAccessToken(String accessToken) {
        if (CommonUtil.isStringEmpty(accessToken)) return null;
        if (!accessToken.contains("Bearer ")) return accessToken;
        return accessToken.split("Bearer ")[1];
    }

    /**
     * 토큰에서 회원 로그인 ID 추출
     * @param token - Access Token
     */
    public String getUserAccountId(String token) {
        String accessToken = getAccessToken(token);
        return Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(accessToken).getBody().getSubject();
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = getJwtUser(token);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    private JwtUserDto getJwtUser(String token) {
        JwtUserDto jwtUserDto = new JwtUserDto(getUserAccountId(token));
        List<GrantedAuthority> authorities = new ArrayList<>();
        // "ROLE_" 가 반드시 있어야 Security가 인식함
        String userRole = "ROLE_USER";
        authorities.add(new SimpleGrantedAuthority(userRole));
        jwtUserDto.setAuthorities(authorities);
        return jwtUserDto;
    }

    /**
     * 토큰의 유효성 + 만료일자 확인
     * @param token - JWT Token
     */
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}