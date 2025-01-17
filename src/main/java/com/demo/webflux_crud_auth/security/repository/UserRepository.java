package com.demo.webflux_crud_auth.security.repository;

import com.demo.webflux_crud_auth.security.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Mono<User> findByUsernameOrEmail(String username, String email);
}
