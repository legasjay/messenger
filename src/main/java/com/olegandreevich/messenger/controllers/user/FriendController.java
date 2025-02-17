package com.olegandreevich.messenger.controllers.user;

import com.olegandreevich.messenger.entities.user.Friend;
import com.olegandreevich.messenger.entities.user.MyUser;
import com.olegandreevich.messenger.servicies.user.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    /** * Отправка запроса на добавление в друзья */
    @PostMapping("/{friendId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Friend> sendFriendRequest(@PathVariable String friendId, @RequestHeader("X-User-ID") String userId) {
        return friendService.sendFriendRequest(userId, friendId);
    }

    /** * Принять запрос на дружбу */
    @PutMapping("/{friendId}/accept")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> acceptFriendRequest(@PathVariable String friendId, @RequestHeader("X-User-ID") String userId) {
        return friendService.acceptFriendRequest(userId, friendId);
    }

    /** * Отклонить запрос на дружбу */
    @PutMapping("/{friendId}/reject")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> rejectFriendRequest(@PathVariable String friendId, @RequestHeader("X-User-ID") String userId) {
        return friendService.rejectFriendRequest(userId, friendId);
    }

    /** * Получить список друзей пользователя */
    @GetMapping
    public Flux<MyUser> getFriends(@RequestHeader("X-User-ID") String userId) {
        return friendService.getFriends(userId);
    }

    /** * Удалить друга */
    @DeleteMapping("/{friendId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> removeFriend(@PathVariable String friendId, @RequestHeader("X-User-ID") String userId) {
        return friendService.removeFriend(userId, friendId);
    }

    /** * Получить список ожидающих запросов на дружбу */
    @GetMapping("/requests")
    public Flux<Friend> getPendingRequests(@RequestHeader("X-User-ID") String userId) {
        return friendService.findPendingRequests(userId);
    }
}
