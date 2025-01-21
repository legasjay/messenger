package com.olegandreevich.messenger.repositories;

import com.olegandreevich.messenger.entities.user.MyUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface MyUserRepository extends ReactiveMongoRepository<MyUser, String> {

    Mono<MyUser> findByUsername(String username);
}
