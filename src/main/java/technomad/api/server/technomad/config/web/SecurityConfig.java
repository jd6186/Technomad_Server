package technomad.api.server.technomad.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import technomad.api.server.technomad.core.auth.JwtAuthProvider;
import technomad.api.server.technomad.core.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthProvider jwtAuthProvider;
    private final CorsConfigurationSource corsConfigurationSource;
    public SecurityConfig(JwtAuthProvider jwtAuthProvider, CorsConfigurationSource corsConfigurationSource){
        this.jwtAuthProvider = jwtAuthProvider;
        this.corsConfigurationSource = corsConfigurationSource;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers().disable().csrf().disable().cors().configurationSource(corsConfigurationSource);
        http.httpBasic().disable().formLogin().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // TODO - 개발 완료 시 주석 해제하고 아래 /** > permitAll은 제거 > 추후 메뉴별로 anyMatchers 만들어야 함
        http.authorizeRequests()
            .requestMatchers("/**").permitAll()
//                .antMatchers("/v3/api-docs/**", "/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/guest/**").permitAll()
//                .antMatchers("/**").hasAnyRole("MASTER", "NORMAL_MANAGER")
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(new JwtAuthenticationFilter(jwtAuthProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}