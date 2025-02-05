package com.olegandreevich.messenger.controllers.chats;

import com.olegandreevich.messenger.entities.chats.Message;
import com.olegandreevich.messenger.servicies.chats.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{chatId}")
    public Mono<ResponseEntity<Flux<Message>>> getMessagesByChatId(@PathVariable("chatId") String chatId) {
        return messageService.findMessagesByChatId(chatId)
                .collectList()
                .map(messages -> new ResponseEntity<>(Flux.fromIterable(messages), HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{chatId}/{id}")
    public Mono<ResponseEntity<Message>> getMessageByIdAndChatId(
            @PathVariable("chatId") String chatId,
            @PathVariable("id") String id) {
        return messageService.findMessageByIdAndChatId(id, chatId)
                .map(message -> new ResponseEntity<>(message, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<Message>> createMessage(@RequestBody Message message) {
        return messageService.saveMessage(message)
                .map(savedMessage -> new ResponseEntity<>(savedMessage, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/{chatId}/{id}")
    public Mono<ResponseEntity<Void>> deleteMessageByIdAndChatId(
            @PathVariable("chatId") String chatId,
            @PathVariable("id") String id) {
        return messageService.deleteMessageByIdAndChatId(id, chatId)
                .<ResponseEntity<Void>>then(Mono.just(new ResponseEntity<>(HttpStatus.NO_CONTENT)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/count/{chatId}")
    public Mono<ResponseEntity<Long>> countMessagesInChat(@PathVariable("chatId") String chatId) {
        return messageService.countMessagesInChat(chatId)
                .map(count -> new ResponseEntity<>(count, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
