package com.olegandreevich.messenger.repositories.user;

import com.olegandreevich.messenger.entities.user.Profile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends ReactiveMongoRepository<Profile, String> {
    // Дополнительные методы могут быть добавлены по мере необходимости
}
