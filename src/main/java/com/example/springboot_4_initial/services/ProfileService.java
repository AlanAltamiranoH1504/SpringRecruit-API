package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.dto.profile.RequestUpdateProfileDTO;
import com.example.springboot_4_initial.dto.profile.ResponseProfileDTO;
import com.example.springboot_4_initial.dto.profile.RquestProfileDTO;
import com.example.springboot_4_initial.exceptions.CreatedEntityException;
import com.example.springboot_4_initial.exceptions.ListEmptyException;
import com.example.springboot_4_initial.exceptions.NotFoundEntity;
import com.example.springboot_4_initial.exceptions.UpdateException;
import com.example.springboot_4_initial.models.Profile;
import com.example.springboot_4_initial.repositories.IProfileRepository;
import com.example.springboot_4_initial.services.interfaces.IProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileService implements IProfileService {
    private final IProfileRepository iProfileRepository;

    @Override
    public List<ResponseProfileDTO> getAllProfiles(boolean status) {
        List<Profile> profiles = iProfileRepository.findAllByStatus(status);
        if (profiles.isEmpty()) {
            throw  new ListEmptyException("Empty profile list");
        }
        return profiles.stream()
                .map(profile -> {
                    return ResponseProfileDTO.builder()
                            .idProfile(profile.getId())
                            .profile(profile.getProfile())
                            .status(profile.isStatus())
                            .build();
                }).toList();
    }

    @Override
    public ResponseProfileDTO getProfile(Long idProfile) {
        Profile profileToFound = iProfileRepository.findById(idProfile)
                .orElseThrow(() -> new NotFoundEntity("Not Found Profile with id: " + idProfile));
        return ResponseProfileDTO.builder()
                .idProfile(profileToFound.getId())
                .profile(profileToFound.getProfile())
                .status(profileToFound.isStatus())
                .build();
    }

    @Override
    public ResponseProfileDTO createProfile(RquestProfileDTO requestProfileDTO) {
        Profile profileByName = iProfileRepository.findByName(requestProfileDTO.getProfile());
        if (profileByName != null) {
            throw new CreatedEntityException("Name Profile already exists");
        }
        Profile profileToSave = Profile.builder()
                .profile(requestProfileDTO.getProfile())
                .status(true)
                .build();
        iProfileRepository.save(profileToSave);
        if (profileToSave.getId() == null){
            throw new CreatedEntityException("Create Profile Failed");
        }

        return ResponseProfileDTO.builder()
                .idProfile(profileToSave.getId())
                .profile(profileToSave.getProfile())
                .status(true)
                .build();
    }

    @Override
    public ResponseProfileDTO updateProfile(Long idProfile, RequestUpdateProfileDTO requestUpdateProfileDTO) {
        Profile profileToUpdate = iProfileRepository.findById(idProfile)
                .orElseThrow(() -> new NotFoundEntity("Not Found Profile with id: " + idProfile));
        Profile profileByName = iProfileRepository.findByName(requestUpdateProfileDTO.getProfile());
        if (profileByName != null && !profileByName.getId().equals(profileToUpdate.getId())) {
            throw new UpdateException("There is a profile with that name");
        }
        BeanUtils.copyProperties(requestUpdateProfileDTO, profileToUpdate);
        iProfileRepository.save(profileToUpdate);
        log.info("Perfil actualizado correctamente");
        return ResponseProfileDTO.builder()
                .idProfile(profileToUpdate.getId())
                .profile(profileToUpdate.getProfile())
                .status(profileToUpdate.isStatus())
                .build();
    }

    @Override
    public void deleteProfile(Long idProfile) {
        Profile profileToDelete = iProfileRepository.findById(idProfile)
                .orElseThrow(() -> new NotFoundEntity("Not Found Profile with id: " + idProfile));
        profileToDelete.setStatus(false);
        iProfileRepository.save(profileToDelete);
    }

    @Override
    public void destroyProfile(Long idProfile) {
        Profile profileToDestroy = iProfileRepository.findById(idProfile)
                .orElseThrow(() -> new NotFoundEntity("Not Found Profile with id: " + idProfile));
        iProfileRepository.delete(profileToDestroy);
    }

    @Override
    public void changeStatus(Long idProfile) {
        Profile profileToChange = iProfileRepository.findById(idProfile)
                .orElseThrow(() -> new NotFoundEntity("Not Found Profile with id: " + idProfile));
        profileToChange.setStatus(!profileToChange.isStatus());
        iProfileRepository.save(profileToChange);
    }
}
