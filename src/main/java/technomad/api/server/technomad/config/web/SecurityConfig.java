package technomad.api.server.technomad.config.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import technomad.api.server.technomad.core.auth.JwtAuthProvider;
import technomad.api.server.technomad.core.filter.JwtAuthenticationFilter;

@EnableWebSecurity
@Slf4j
public class SecurityConfig {
    private final JwtAuthProvider jwtAuthProvider;
    private final CorsConfigurationSource corsConfigurationSource;

    public SecurityConfig(JwtAuthProvider jwtAuthProvider, CorsConfigurationSource corsConfigurationSource){
        this.jwtAuthProvider = jwtAuthProvider;
        this.corsConfigurationSource = corsConfigurationSource;
    }

    /**
     * Security Filter Chain을 통해 인증 관리
     * Security 적용 Tip
     * antMatchers 작성 시 permitAll 하고 싶은 목록이 차단하는 URL 경로보다 먼저 작성되어야 정상적으로 permitAll을 적용 가능
     * Security를 활용해 비허가 요청을 정상적으로 차단하기 위해선 .anyRequest().authenticated() 를 반드시 작성해 기능을 작동시켜야 함
     * @param http - Spring이 관리하는 곳으로 진입하는 모든 Http 요청
     * @throws Exception - 예외
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.headers().disable() // Base64 File 전송
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource)
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                // TODO - 개발 완료 시 주석 해제하고 아래 /** > permitAll은 제거 > 추후 메뉴별로 anyMatchers 만들어야 함
                .requestMatchers("/**").permitAll()
//                .antMatchers("/v3/api-docs/**", "/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/guest/**").permitAll()
//                .antMatchers("/**").hasAnyRole("MASTER", "NORMAL_MANAGER")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtAuthProvider), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}