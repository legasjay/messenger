package com.olegandreevich.messenger.servicies.user;

import com.mongodb.DuplicateKeyException;
import com.olegandreevich.messenger.entities.user.MyUser;
import com.olegandreevich.messenger.entities.user.Profile;
import com.olegandreevich.messenger.repositories.user.MyUserRepository;
import org.bson.BsonDocument;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MyUserService {

    private static final Logger logger = LoggerFactory.getLogger(MyUserService.class);

    private final MyUserRepository userRepository;

    public MyUserService(MyUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public Flux<MyUser> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Mono<MyUser> findUserById(String id) {
        return userRepository.findById(id).switchIfEmpty(
                Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"))
        );
    }

    @Transactional(readOnly = true)
    public Mono<MyUser> findUserByUsername(String username) {
        return userRepository.findByUsername(username).switchIfEmpty(
                Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "User with given username not found"))
        );
    }

    @Transactional(readOnly = true)
    public Mono<MyUser> findUserByEmail(String email) {
        return userRepository.findByEmail(email).switchIfEmpty(
                Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "User with given email not found"))
        );
    }

    @Transactional
    public Mono<MyUser> saveUser(MyUser user) {
        if (user == null || user.getUsername() == null || user.getEmail() == null) {
            throw new IllegalArgumentException("Invalid user data");
        }

        return userRepository.findByUsername(user.getUsername())
                .hasElement()
                .flatMap(hasUsername -> hasUsername ? Mono.error(new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists")) : Mono.just(true))
                .then(userRepository.findByEmail(user.getEmail()))
                .hasElement()
                .flatMap(hasEmail -> hasEmail ? Mono.error(new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists")) : Mono.just(true))
                .then(userRepository.save(user));
    }

    @Transactional
    public Mono<Void> deleteUserById(String id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")))
                .flatMap(userRepository::delete);
    }

    @Transactional
    public Mono<MyUser> updateUser(String id, MyUser myUser) {
        if (myUser == null || myUser.getUsername() == null || myUser.getEmail() == null) {
            throw new IllegalArgumentException("Invalid user data");
        }

        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")))
                .flatMap(existingUser -> {
                    existingUser.setUsername(myUser.getUsername());
                    existingUser.setEmail(myUser.getEmail());
                    existingUser.setPassword(myUser.getPassword());
                    return userRepository.save(existingUser);
                })
                .onErrorResume(Throwable.class, e -> {
                    logger.error("Error updating user: {}", e.getMessage(), e);
                    return Mono.error(e);
                });
    }
}
