package com.tutorial.tutorial_webflux.repository;

import com.tutorial.tutorial_webflux.entity.Product;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
    Mono<Product> findByName(String name);

    @Query("SELECT * FROM products WHERE id <> :id AND name = :name")
    Mono<Product> repeatedName(long id, String name);
}
