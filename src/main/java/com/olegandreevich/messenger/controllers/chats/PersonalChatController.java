package com.olegandreevich.messenger.controllers.chats;

import com.olegandreevich.messenger.entities.chats.PersonalChat;
import com.olegandreevich.messenger.dto.chats.ParticipantInfo;
import com.olegandreevich.messenger.servicies.chats.PersonalChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/personal-chats")
public class PersonalChatController {

    private final PersonalChatService personalChatService;

    public PersonalChatController(PersonalChatService personalChatService) {
        this.personalChatService = personalChatService;
    }

    @GetMapping
    public Mono<ResponseEntity<Flux<PersonalChat>>> getAllPersonalChats() {
        return personalChatService.findAllPersonalChats()
                .collectList()
                .map(personalChats -> new ResponseEntity<>(Flux.fromIterable(personalChats), HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<PersonalChat>> getPersonalChatById(@PathVariable("id") String id) {
        return personalChatService.findPersonalChatById(id)
                .map(personalChat -> new ResponseEntity<>(personalChat, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/participant/{participantId}")
    public Mono<ResponseEntity<PersonalChat>> getPersonalChatByParticipant(@PathVariable("participantId") String participantId) {
        ParticipantInfo participant = new ParticipantInfo(); //
        participant.setUserId(participantId);                    // с нужным ID
        return personalChatService.findPersonalChatByParticipant(participant)
                .map(personalChat -> new ResponseEntity<>(personalChat, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<PersonalChat>> createPersonalChat(@RequestBody PersonalChat personalChat) {
        return personalChatService.savePersonalChat(personalChat)
                .map(savedPersonalChat -> new ResponseEntity<>(savedPersonalChat, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deletePersonalChatById(@PathVariable("id") String id) {
        return personalChatService.deletePersonalChatById(id)
                .<ResponseEntity<Void>>then(Mono.just(new ResponseEntity<>(HttpStatus.NO_CONTENT)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
