package io.github.lucasefdr.DDSpringBootEssentials.service;

import io.github.lucasefdr.DDSpringBootEssentials.repository.UserAPIRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * As classes de <strong>serviços</strong> são responsáveis pela <strong>regra de negócio da aplicação</strong>. São anotadas com a annotation <strong>@Service</strong> <br>
 */
@Service
@RequiredArgsConstructor
public class UserAPIService implements UserDetailsService {
    private final UserAPIRepository userAPIRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return Optional.ofNullable(userAPIRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
