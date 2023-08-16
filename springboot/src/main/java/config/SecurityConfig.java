package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    protected DefaultSecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(requests -> requests
                        .requestMatchers(isAllowed())
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll());

        // You might also want to disable CSRF temporarily for simplicity.
        http.csrf().disable();

        return http.build();
    }

    private RequestMatcher isAllowed() {
        return request -> {
            String path = request.getServletPath();
            return "/".equals(path) || "/index.html".equals(path) ||
                    "/register.html".equals(path) || "/login.html".equals(path);
        };
    }
}
