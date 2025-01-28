package com.olegandreevich.messenger.servicies.user;

import com.olegandreevich.messenger.entities.user.Profile;
import com.olegandreevich.messenger.repositories.user.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<Profile> findAllProfiles() {
        return profileRepository.findAll();
    }

    public Optional<Profile> findById(String id) {
        return profileRepository.findById(id);
    }

    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public void deleteProfileById(String id) {
        profileRepository.deleteById(id);
    }

    public boolean existsById(String id) {
        return profileRepository.existsById(id);
    }

    public long count() {
        return profileRepository.count();
    }

    // Метод для создания профиля с проверкой существования пользователя
    public Profile createProfile(Profile profile) throws Exception {
        if (existsById(profile.getId())) {
            throw new Exception("Профиль с таким ID уже существует");
        }
        return profileRepository.save(profile);
    }

    // Метод для обновления профиля с проверками
    public Profile updateProfile(String id, Profile profileDetails) throws Exception {
        Optional<Profile> existingProfile = findById(id);
        if (existingProfile.isEmpty()) {
            throw new Exception("Профиль с указанным ID не найден");
        }

        Profile existingProfileObj = existingProfile.get();
        existingProfileObj.setFirstName(profileDetails.getFirstName());
        existingProfileObj.setLastName(profileDetails.getLastName());
        existingProfileObj.setBirthDate(profileDetails.getBirthDate());
        existingProfileObj.setAvatar(profileDetails.getAvatar());
        existingProfileObj.setDescription(profileDetails.getDescription());
        existingProfileObj.setUserId(profileDetails.getUserId());

        return profileRepository.save(existingProfileObj);
    }

    // Метод для удаления профиля с проверкой его существования
    public void deleteProfile(String id) throws Exception {
        if (!existsById(id)) {
            throw new Exception("Профиль с указанным ID не найден");
        }
        profileRepository.deleteById(id);
    }
}
