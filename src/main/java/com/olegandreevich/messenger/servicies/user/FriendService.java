package com.olegandreevich.messenger.servicies.user;

import com.olegandreevich.messenger.entities.enums.FriendStatus;
import com.olegandreevich.messenger.entities.user.Friend;
import com.olegandreevich.messenger.entities.user.MyUser;
import com.olegandreevich.messenger.repositories.user.FriendRepository;
import com.olegandreevich.messenger.repositories.user.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final MyUserRepository userRepository;

    /** * Отправка запроса на добавление в друзья */
    public Mono<Friend> sendFriendRequest(String userId, String friendId) {
        return friendRepository.findByUserIdAndFriendId(userId, friendId)
                .hasElement()
                .filter(exists -> !exists)
                .flatMap(exists -> {
                    if (!exists) {
                        Friend friend = new Friend();
                        friend.setUserId(userId);
                        friend.setFriendId(friendId);
                        friend.setStatus(FriendStatus.REQUESTED);
                        friend.setRequestedAt(LocalDateTime.now());
                        return friendRepository.save(friend);
                    } else {
                        return Mono.error(new IllegalStateException("Запрос на дружбу уже отправлен"));
                    }
                });
    }

    /** * Принять запрос на дружбу */
    public Mono<Void> acceptFriendRequest(String userId, String friendId) {
        return friendRepository.findByUserIdAndFriendId(userId, friendId)
                .switchIfEmpty(Mono.error(new RuntimeException("Запрос на дружбу не найден")))
                .flatMap(friend -> {
                    if (friend.getStatus() == FriendStatus.ACCEPTED) {
                        return Mono.empty(); // Дружба уже подтверждена
                    } else {
                        friend.setStatus(FriendStatus.ACCEPTED);
                        return friendRepository.save(friend).then();
                    }
                });
    }

    /** * Отклонить запрос на дружбу */
    public Mono<Void> rejectFriendRequest(String userId, String friendId) {
        return friendRepository.findByUserIdAndFriendId(userId, friendId)
                .switchIfEmpty(Mono.error(new RuntimeException("Запрос на дружбу не найден")))
                .flatMap(friend -> {
                    if (friend.getStatus() == FriendStatus.REJECTED) {
                        return Mono.empty(); // Запрос уже отклонен
                    } else {
                        friend.setStatus(FriendStatus.REJECTED);
                        return friendRepository.save(friend).then();
                    }
                });
    }

    /** * Получить список друзей пользователя */
    public Flux<MyUser> getFriends(String userId) {
        return friendRepository.findByUserIdAndStatus(userId, FriendStatus.ACCEPTED)
                .flatMap(friend -> userRepository.findById(friend.getFriendId()));
    }

    /** * Удалить друга */
    public Mono<Void> removeFriend(String userId, String friendId) {
        return friendRepository.findByUserIdAndFriendId(userId, friendId)
                .switchIfEmpty(Mono.error(new RuntimeException("Дружбы не существует")))
                .flatMap(friend -> {
                    friend.setStatus(FriendStatus.REJECTED);
                    return friendRepository.save(friend).then();
                });
    }

    /** * Поиск запросов на дружбу */
    public Flux<Friend> findPendingRequests(String userId) {
        return friendRepository.findByFriendIdAndStatus(userId, FriendStatus.REQUESTED);
    }
}
