package com.olegandreevich.messenger.repositories.chats;

import com.olegandreevich.messenger.entities.chats.GroupChat;
import com.olegandreevich.messenger.entities.dto.chats.ParticipantInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GroupChatRepository extends ReactiveMongoRepository<GroupChat, String> {

    Mono<GroupChat> findByName(String name);

    Flux<GroupChat> findByIsPublic(boolean isPublic);

    Mono<GroupChat> findByAdminsContains(ParticipantInfo admin);

    Mono<GroupChat> findByParticipantsContains(ParticipantInfo participant);
}
