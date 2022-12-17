package io.github.lucasefdr.DDSpringBootEssentials.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

/**
 * Annotation para configuração de segurança <br>
 * {@link EnableGlobalMethodSecurity} - habilitar a verificação nos endpoints
 */
// @EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
@Log4j2
public class SecurityConfigInMemory {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //@formatter:off
        http.csrf().disable() // csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .authorizeHttpRequests((authz) -> authz.anyRequest()
                .authenticated())
                .formLogin()
                .and()
                .httpBasic();
        //@formatter:on

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers();
    }

    @Bean
    public InMemoryUserDetailsManager users(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails user1 = User
                .withUsername("lucasefdr")
                .password(passwordEncoder.encode("pass"))
                .roles("USER", "ADMIN")
                .build();

        UserDetails user2 = User
                .withUsername("laramfdr")
                .password(passwordEncoder.encode("pass"))
                .roles("USER")
                .build();

        log.info(passwordEncoder.encode("pass"));

        List<UserDetails> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        return new InMemoryUserDetailsManager(users);
    }
}

