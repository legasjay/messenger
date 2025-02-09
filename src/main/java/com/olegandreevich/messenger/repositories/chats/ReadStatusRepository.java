package com.olegandreevich.messenger.repositories.chats;

import com.olegandreevich.messenger.entities.chats.ReadStatus;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReadStatusRepository extends ReactiveMongoRepository<ReadStatus, String> {

    Mono<ReadStatus> findByMessageIdAndUserId(String messageId, String userId);

    Flux<ReadStatus> findByMessageId(String messageId);

    Flux<ReadStatus> findByUserId(String userId);
}
