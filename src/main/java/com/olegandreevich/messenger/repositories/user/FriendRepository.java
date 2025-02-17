package com.olegandreevich.messenger.repositories.user;

import com.olegandreevich.messenger.entities.enums.FriendStatus;
import com.olegandreevich.messenger.entities.user.Friend;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FriendRepository extends ReactiveMongoRepository<Friend, String> {

    /** * Найти всех друзей пользователя с определенным статусом */
    Flux<Friend> findByUserIdAndStatus(String userId, FriendStatus status);

    /** * Найти запись о дружбе по идентификаторам обоих пользователей */
    Mono<Friend> findByUserIdAndFriendId(String userId, String friendId);

    /** * Найти запись о дружбе по идентификатору второго пользователя и статусу */
    Flux<Friend> findByFriendIdAndStatus(String friendId, FriendStatus status);
}
