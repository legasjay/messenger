package com.olegandreevich.messenger.servicies.user;

import com.olegandreevich.messenger.entities.user.Profile;
import com.olegandreevich.messenger.repositories.user.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Mono<Profile> createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public Mono<Profile> getProfileById(String id) {
        return profileRepository.findById(id);
    }

    public Flux<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Mono<Profile> updateProfile(String id, Profile profile) {
        return profileRepository.findById(id)
                .flatMap(existingProfile -> {
                    existingProfile.setFirstName(profile.getFirstName());
                    existingProfile.setLastName(profile.getLastName());
                    existingProfile.setBirthDate(profile.getBirthDate());
                    existingProfile.setAvatar(profile.getAvatar());
                    existingProfile.setDescription(profile.getDescription());
                    existingProfile.setUserId(profile.getUserId());
                    return profileRepository.save(existingProfile);
                });
    }

    public Mono<Void> deleteProfile(String id) {
        return profileRepository.deleteById(id);
    }
}
