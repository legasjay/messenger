package com.olegandreevich.messenger.servicies.chats;

import com.olegandreevich.messenger.entities.chats.ReadStatus;
import com.olegandreevich.messenger.repositories.chats.ReadStatusRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReadStatusService {

    private final ReadStatusRepository readStatusRepository;

    public ReadStatusService(ReadStatusRepository readStatusRepository) {
        this.readStatusRepository = readStatusRepository;
    }

    @Transactional(readOnly = true)
    public Flux<ReadStatus> findAllReadStatuses() {
        return readStatusRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Mono<ReadStatus> findReadStatusById(String id) {
        return readStatusRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Mono<ReadStatus> findReadStatusByMessageIdAndUserId(String messageId, String userId) {
        return readStatusRepository.findByMessageIdAndUserId(messageId, userId);
    }

    @Transactional(readOnly = true)
    public Flux<ReadStatus> findReadStatusesByMessageId(String messageId) {
        return readStatusRepository.findByMessageId(messageId);
    }

    @Transactional(readOnly = true)
    public Flux<ReadStatus> findReadStatusesByUserId(String userId) {
        return readStatusRepository.findByUserId(userId);
    }

    @Transactional
    public Mono<ReadStatus> saveReadStatus(ReadStatus readStatus) {
        return readStatusRepository.save(readStatus);
    }

    @Transactional
    public Mono<Void> deleteReadStatusById(String id) {
        return readStatusRepository.deleteById(id);
    }
}
