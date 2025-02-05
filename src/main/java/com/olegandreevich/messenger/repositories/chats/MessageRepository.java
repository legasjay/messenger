package com.olegandreevich.messenger.repositories.chats;

import com.olegandreevich.messenger.entities.chats.Message;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MessageRepository extends ReactiveMongoRepository<Message, String> {

    Mono<Message> findByIdAndChatId(String id, String chatId);

    Flux<Message> findByChatId(String chatId);

    Mono<Long> countByChatId(String chatId);

    Mono<Void> deleteByIdAndChatId(String id, String chatId);
}
