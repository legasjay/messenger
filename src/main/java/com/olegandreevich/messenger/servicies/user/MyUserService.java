package com.olegandreevich.messenger.servicies.user;

import com.olegandreevich.messenger.entities.user.MyUser;
import com.olegandreevich.messenger.repositories.user.MyUserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MyUserService {

    private final MyUserRepository myUserRepository;

    public MyUserService(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    public Flux<MyUser> getAllUsers() {
        return myUserRepository.findAll();
    }

    public Mono<MyUser> getUserById(String id) {
        return myUserRepository.findById(id);
    }

    public Mono<MyUser> createUser(MyUser user) {
        return myUserRepository.save(user);
    }

    public Mono<Void> deleteUser(String id) {
        return myUserRepository.deleteById(id);
    }

    public Mono<MyUser> updateUser(String id, MyUser updatedUser) {
        return myUserRepository.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setEmail(updatedUser.getEmail());
                    existingUser.setPassword(updatedUser.getPassword()); // Важно: нужно обновлять только нужные поля!
                    return myUserRepository.save(existingUser);
                });
    }

    public Mono<MyUser> findByUsername(String username) {
        return myUserRepository.findByUsername(username);
    }
}
