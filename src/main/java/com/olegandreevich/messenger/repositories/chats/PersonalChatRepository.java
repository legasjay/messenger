package com.olegandreevich.messenger.repositories.chats;

import com.olegandreevich.messenger.entities.chats.PersonalChat;
import com.olegandreevich.messenger.dto.chats.ParticipantInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface PersonalChatRepository extends ReactiveMongoRepository<PersonalChat, String> {

    Mono<PersonalChat> findByParticipantsContains(ParticipantInfo participant);
}
