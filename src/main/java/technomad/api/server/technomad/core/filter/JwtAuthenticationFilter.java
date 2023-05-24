package technomad.api.server.technomad.core.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import technomad.api.server.technomad.core.auth.JwtAuthProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import technomad.api.server.technomad.core.code.ErrorCode;
import technomad.api.server.technomad.core.error.CustomException;
import technomad.api.server.technomad.core.util.CommonUtil;

import java.util.Enumeration;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtAuthProvider jwtAuthProvider;
    public JwtAuthenticationFilter(JwtAuthProvider jwtAuthProvider){
        this.jwtAuthProvider = jwtAuthProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain){
        log.info("==================doFilter================================");
        String uri = req.getRequestURI();
        log.info("uri = " + uri);

        Enumeration<String> headerNames = req.getHeaderNames();
        // TODO - 아래 While문 운영에는 배포 금지
        while(headerNames.hasMoreElements()){
            String name= headerNames.nextElement();
            log.info("Header Name : " + name + ", Value : " + req.getHeader(name));
        }

        try {
            String acToken = jwtAuthProvider.getAccessToken(req.getHeader("authorization"));
            resp.setHeader("accessToken", acToken);
            String reToken = req.getHeader("refreshToken");
            log.info("acToken ===="+acToken);
            log.info("reToken ===="+reToken);
            tokenCheck(resp, acToken, reToken);
            filterChain.doFilter(req, resp);
        } catch (Exception e) {
            // 인증관련 로직에서 Error가 발생했으므로 401 status return
            log.error("에러발생");
            e.printStackTrace();
        }
    }

    private void tokenCheck(HttpServletResponse resp, String acToken, String reToken) throws CustomException {
        // Access Token Check - 없으면 그냥 PASS 시키고 어차피 Security Config에서 토큰이 없어 장애가 발생할 것이므로 따로 체크는 안함
        if(!CommonUtil.isStringEmpty(acToken)){
            // Access Token이 만료됐을 경우 Refresh Token Check
            if(!jwtAuthProvider.validateToken(acToken)){
                if(CommonUtil.isStringEmpty(reToken) || !jwtAuthProvider.validateToken(reToken)) {
                    throw new CustomException(ErrorCode.TOKEN_EXPIRATION, this.getClass().getName());
                }else {
                    tokenValidationCheck(resp, acToken, reToken);
                }
            }
            Authentication authentication = jwtAuthProvider.getAuthentication(acToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private void tokenValidationCheck(HttpServletResponse resp, String acToken, String reToken){
        // Access Token이 만료된 경우 RefreshToken 검사
        if(!jwtAuthProvider.validateToken(acToken)){
            if(!jwtAuthProvider.validateToken(reToken)){
                log.error("Access Token 만료 후 Refresh Token이 만료 > 401 status return", 401, resp);
                throw new IllegalArgumentException("만료된 토큰");
            }
            // Refresh Token이 살아있는 경우 모든 Token 갱신
            String managerAccountId = jwtAuthProvider.getUserAccountId(reToken);
            tokenRefresh(resp, managerAccountId);
        }
    }

    private void tokenRefresh(HttpServletResponse resp, String managerAccountId) {
        String accessToken = jwtAuthProvider.createAccessToken(managerAccountId);
        String refreshToken = jwtAuthProvider.createRefreshToken(managerAccountId);
        if(CommonUtil.isStringEmpty(accessToken)){

        }
        resp.setHeader("accessToken", accessToken);
        resp.setHeader("refreshToken", refreshToken);
    }
}