package com.olegandreevich.messenger.servicies.chats;

import com.olegandreevich.messenger.entities.chats.GroupChat;
import com.olegandreevich.messenger.entities.dto.ParticipantInfo;
import com.olegandreevich.messenger.repositories.chats.GroupChatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class GroupChatService {

    private final GroupChatRepository groupChatRepository;

    public GroupChatService(GroupChatRepository groupChatRepository) {
        this.groupChatRepository = groupChatRepository;
    }

    @Transactional(readOnly = true)
    public Flux<GroupChat> findAllGroupChats() {
        return groupChatRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Mono<GroupChat> findGroupChatById(String id) {
        return groupChatRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Mono<GroupChat> findGroupChatByName(String name) {
        return groupChatRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Flux<GroupChat> findPublicGroupChats() {
        return groupChatRepository.findByIsPublic(true);
    }

    @Transactional(readOnly = true)
    public Mono<GroupChat> findGroupChatByAdmin(ParticipantInfo admin) {
        return groupChatRepository.findByAdminsContains(admin);
    }

    @Transactional(readOnly = true)
    public Mono<GroupChat> findGroupChatByParticipant(ParticipantInfo participant) {
        return groupChatRepository.findByParticipantsContains(participant);
    }

    @Transactional
    public Mono<GroupChat> saveGroupChat(GroupChat groupChat) {
        return groupChatRepository.save(groupChat);
    }

    @Transactional
    public Mono<Void> deleteGroupChatById(String id) {
        return groupChatRepository.deleteById(id);
    }

    @Transactional
    public Mono<GroupChat> addParticipant(String chatId, String participantId) {
        return groupChatRepository.findById(chatId)
                .flatMap(groupChat -> {
                    ParticipantInfo participant = new ParticipantInfo();
                    participant.setUserId(participantId); // Используем только идентификатор пользователя
                    groupChat.getParticipants().add(participant);
                    return groupChatRepository.save(groupChat);
                });
    }

    @Transactional
    public Mono<Void> removeParticipant(String chatId, String participantId) {
        return groupChatRepository.findById(chatId)
                .flatMap(groupChat -> {
                    Optional<ParticipantInfo> optionalParticipant = groupChat.getParticipants()
                            .stream()
                            .filter(p -> p.getUserId().equals(participantId)) // Ищем участника по его идентификатору
                            .findFirst(); // Получаем первого найденного участника

                    if (optionalParticipant.isPresent()) {
                        groupChat.getParticipants().remove(optionalParticipant.get()); // Удаляем участника
                        return groupChatRepository.save(groupChat).then(); // Сохраняем изменения
                    } else {
                        return Mono.empty(); // Участник не найден
                    }
                })
                .switchIfEmpty(Mono.empty());
    }

    @Transactional(readOnly = true)
    public Flux<ParticipantInfo> getParticipants(String chatId) {
        return groupChatRepository.findById(chatId)
                .flatMapMany(groupChat -> Flux.fromIterable(groupChat.getParticipants()));
    }
}