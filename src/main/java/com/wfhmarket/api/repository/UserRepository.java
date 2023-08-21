package com.wfhmarket.api.repository;

import com.wfhmarket.api.model.Member;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@Repository
public interface UserRepository extends ReactiveCrudRepository<Member, String> {
    Mono<Member> findByEmail(String email);
}
