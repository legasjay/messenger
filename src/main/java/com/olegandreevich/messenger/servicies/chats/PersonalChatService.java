package com.olegandreevich.messenger.servicies.chats;

import com.olegandreevich.messenger.entities.chats.PersonalChat;
import com.olegandreevich.messenger.dto.chats.ParticipantInfo;
import com.olegandreevich.messenger.repositories.chats.PersonalChatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonalChatService {

    private final PersonalChatRepository personalChatRepository;

    public PersonalChatService(PersonalChatRepository personalChatRepository) {
        this.personalChatRepository = personalChatRepository;
    }

    @Transactional(readOnly = true)
    public Flux<PersonalChat> findAllPersonalChats() {
        return personalChatRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Mono<PersonalChat> findPersonalChatById(String id) {
        return personalChatRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Mono<PersonalChat> findPersonalChatByParticipant(ParticipantInfo participant) {
        return personalChatRepository.findByParticipantsContains(participant);
    }

    @Transactional
    public Mono<PersonalChat> savePersonalChat(PersonalChat personalChat) {
        return personalChatRepository.save(personalChat);
    }

    @Transactional
    public Mono<Void> deletePersonalChatById(String id) {
        return personalChatRepository.deleteById(id);
    }
}
