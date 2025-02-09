package com.olegandreevich.messenger.controllers.chats;

import com.olegandreevich.messenger.entities.chats.ReadStatus;
import com.olegandreevich.messenger.servicies.chats.ReadStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/read-statuses")
public class ReadStatusController {

    private final ReadStatusService readStatusService;

    public ReadStatusController(ReadStatusService readStatusService) {
        this.readStatusService = readStatusService;
    }

    @GetMapping
    public Mono<ResponseEntity<Flux<ReadStatus>>> getAllReadStatuses() {
        return readStatusService.findAllReadStatuses()
                .collectList()
                .map(readStatuses -> new ResponseEntity<>(Flux.fromIterable(readStatuses), HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ReadStatus>> getReadStatusById(@PathVariable("id") String id) {
        return readStatusService.findReadStatusById(id)
                .map(readStatus -> new ResponseEntity<>(readStatus, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/message/{messageId}/user/{userId}")
    public Mono<ResponseEntity<ReadStatus>> getReadStatusByMessageIdAndUserId(
            @PathVariable("messageId") String messageId,
            @PathVariable("userId") String userId) {
        return readStatusService.findReadStatusByMessageIdAndUserId(messageId, userId)
                .map(readStatus -> new ResponseEntity<>(readStatus, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/message/{messageId}")
    public Mono<ResponseEntity<Flux<ReadStatus>>> getReadStatusesByMessageId(@PathVariable("messageId") String messageId) {
        return readStatusService.findReadStatusesByMessageId(messageId)
                .collectList()
                .map(readStatuses -> new ResponseEntity<>(Flux.fromIterable(readStatuses), HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/{userId}")
    public Mono<ResponseEntity<Flux<ReadStatus>>> getReadStatusesByUserId(@PathVariable("userId") String userId) {
        return readStatusService.findReadStatusesByUserId(userId)
                .collectList()
                .map(readStatuses -> new ResponseEntity<>(Flux.fromIterable(readStatuses), HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<ReadStatus>> createReadStatus(@RequestBody ReadStatus readStatus) {
        return readStatusService.saveReadStatus(readStatus)
                .map(savedReadStatus -> new ResponseEntity<>(savedReadStatus, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteReadStatusById(@PathVariable("id") String id) {
        return readStatusService.deleteReadStatusById(id)
                .<ResponseEntity<Void>>then(Mono.just(new ResponseEntity<>(HttpStatus.NO_CONTENT)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
