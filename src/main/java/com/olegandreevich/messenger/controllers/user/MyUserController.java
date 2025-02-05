package com.olegandreevich.messenger.controllers.user;

import com.olegandreevich.messenger.entities.user.MyUser;

import com.olegandreevich.messenger.servicies.user.MyUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
public class MyUserController {

    private final MyUserService myUserService;

    public MyUserController(MyUserService userService) {
        this.myUserService = userService;
    }

    @GetMapping
    public Mono<ResponseEntity<Flux<MyUser>>> getAllUsers() {
        return myUserService.findAllUsers()
                .collectList()
                .map(users -> new ResponseEntity<>(Flux.fromIterable(users), HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<MyUser>> getUserById(@PathVariable("id") String id) {
        return myUserService.findUserById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<MyUser>> createUser(@RequestBody MyUser user) {
        return myUserService.saveUser(user)
                .map(savedUser -> new ResponseEntity<>(savedUser, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUserById(@PathVariable("id") String id) {
        return myUserService.deleteUserById(id)
                .<ResponseEntity<Void>>then(Mono.just(new ResponseEntity<>(HttpStatus.NO_CONTENT)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
