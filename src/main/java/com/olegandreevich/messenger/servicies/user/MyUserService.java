package com.olegandreevich.messenger.servicies.user;

import com.olegandreevich.messenger.entities.user.MyUser;
import com.olegandreevich.messenger.repositories.user.MyUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MyUserService {

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
        return userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Mono<MyUser> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Mono<MyUser> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public Mono<MyUser> saveUser(MyUser user) {
        return userRepository.save(user);
    }

    @Transactional
    public Mono<Void> deleteUserById(String id) {
        return userRepository.deleteById(id);
    }
}
