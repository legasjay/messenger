package com.olegandreevich.messenger.controllers.user;


import com.olegandreevich.messenger.entities.user.Profile;
import com.olegandreevich.messenger.servicies.user.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public Mono<ResponseEntity<Profile>> createProfile(@RequestBody Profile profile) {
        return profileService.createProfile(profile)
                .map(savedProfile -> ResponseEntity.status(HttpStatus.CREATED).body(savedProfile))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Profile>> getProfileById(@PathVariable String id) {
        return profileService.getProfileById(id)
                .map(profile -> ResponseEntity.ok(profile))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Удаление профиля", description = "Удаляет профиль по указанному идентификатору.")
    @GetMapping
    public Flux<Profile> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Profile>> updateProfile(@PathVariable String id, @RequestBody Profile profile) {
        return profileService.updateProfile(id, profile)
                .map(updatedProfile -> ResponseEntity.ok(updatedProfile))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteProfile(@PathVariable String id) {
        return profileService.deleteProfile(id)
                .<ResponseEntity<Void>>then(Mono.just(ResponseEntity.noContent().build()))
                .onErrorReturn(ResponseEntity.notFound().build());
    }
}