package com.olegandreevich.messenger.servicies.chats;

import com.olegandreevich.messenger.entities.chats.Message;
import com.olegandreevich.messenger.repositories.chats.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional(readOnly = true)
    public Flux<Message> findMessagesByChatId(String chatId) {
        return messageRepository.findByChatId(chatId);
    }

    @Transactional(readOnly = true)
    public Mono<Message> findMessageByIdAndChatId(String id, String chatId) {
        return messageRepository.findByIdAndChatId(id, chatId);
    }

    @Transactional
    public Mono<Message> saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Transactional
    public Mono<Void> deleteMessageByIdAndChatId(String id, String chatId) {
        return messageRepository.deleteByIdAndChatId(id, chatId);
    }

    @Transactional(readOnly = true)
    public Mono<Long> countMessagesInChat(String chatId) {
        return messageRepository.countByChatId(chatId);
    }
}
