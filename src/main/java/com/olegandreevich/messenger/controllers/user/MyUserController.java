package com.olegandreevich.messenger.controllers.user;

import com.olegandreevich.messenger.entities.user.MyUser;

import com.olegandreevich.messenger.servicies.user.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
public class MyUserController {

    @Autowired
    private MyUserService myUserService;

    @GetMapping
    public Flux<MyUser> getAllUsers() {
        return myUserService.getAllUsers();
    }

    @PostMapping
    public Mono<MyUser> createUser(@RequestBody MyUser user) {
        return myUserService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable String id) {
        return myUserService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public Mono<MyUser> updateUser(@PathVariable String id, @RequestBody MyUser updatedUser) {
        return myUserService.updateUser(id, updatedUser);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<MyUser>> getUserById(@PathVariable String id) {
        return myUserService.getUserById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
