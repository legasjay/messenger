package com.olegandreevich.messenger.repositories.user;

import com.olegandreevich.messenger.entities.user.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {
    Profile findByUserId(String userId);
}
