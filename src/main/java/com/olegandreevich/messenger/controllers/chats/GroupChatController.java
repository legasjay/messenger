package com.olegandreevich.messenger.controllers.chats;

import com.olegandreevich.messenger.entities.chats.GroupChat;
import com.olegandreevich.messenger.servicies.chats.GroupChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/group-chats")
public class GroupChatController {

    private final GroupChatService groupChatService;

    public GroupChatController(GroupChatService groupChatService) {
        this.groupChatService = groupChatService;
    }

    @GetMapping
    public Mono<ResponseEntity<Flux<GroupChat>>> getAllGroupChats() {
        return groupChatService.findAllGroupChats()
                .collectList()
                .map(groupChats -> new ResponseEntity<>(Flux.fromIterable(groupChats), HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<GroupChat>> getGroupChatById(@PathVariable("id") String id) {
        return groupChatService.findGroupChatById(id)
                .map(groupChat -> new ResponseEntity<>(groupChat, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name/{name}")
    public Mono<ResponseEntity<GroupChat>> getGroupChatByName(@PathVariable("name") String name) {
        return groupChatService.findGroupChatByName(name)
                .map(groupChat -> new ResponseEntity<>(groupChat, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/public")
    public Mono<ResponseEntity<Flux<GroupChat>>> getPublicGroupChats() {
        return groupChatService.findPublicGroupChats()
                .collectList()
                .map(publicGroupChats -> new ResponseEntity<>(Flux.fromIterable(publicGroupChats), HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<GroupChat>> createGroupChat(@RequestBody GroupChat groupChat) {
        return groupChatService.saveGroupChat(groupChat)
                .map(savedGroupChat -> new ResponseEntity<>(savedGroupChat, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteGroupChatById(@PathVariable("id") String id) {
        return groupChatService.deleteGroupChatById(id)
                .<ResponseEntity<Void>>then(Mono.just(new ResponseEntity<>(HttpStatus.NO_CONTENT)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
