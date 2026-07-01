package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.dto.user.RequestUserDTO;
import com.example.springboot_4_initial.dto.user.ResponseUserDTO;
import com.example.springboot_4_initial.dto.user.recruiter.RequestRecruiterDTO;
import com.example.springboot_4_initial.dto.user.recruiter.ResponseRecruiterDTO;
import com.example.springboot_4_initial.models.Profile;
import com.example.springboot_4_initial.models.users.User;
import com.example.springboot_4_initial.repositories.IProfileRepository;
import com.example.springboot_4_initial.repositories.IRecruiterRepository;
import com.example.springboot_4_initial.repositories.IUserRepository;
import com.example.springboot_4_initial.services.interfaces.IRecruiterService;
import com.example.springboot_4_initial.services.interfaces.IUserSevice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserSevice {
    private final IUserRepository iUserRepository;
    private final IProfileRepository iProfileRepository;
    private final IRecruiterService iRecruiterService;

    @Override
    public List<ResponseUserDTO> getAllUsers(boolean status) {
        return List.of();
    }

    @Override
    public ResponseUserDTO getUserById(Long idUser) {
        return null;
    }

    @Override
    public ResponseUserDTO createUser(RequestUserDTO requestUserDTO, boolean isRecruiter) {
        ResponseRecruiterDTO responseRecruiterDTO = null;
        Profile profileRecruiter = null;

        User userToSave = User.builder()
                .email(requestUserDTO.getEmail())
                .password(requestUserDTO.getPassword())
                .status(true)
                .build();
        iUserRepository.save(userToSave);
        if (isRecruiter) {
            profileRecruiter = iProfileRepository.findByName("ROLE_RECLUTADOR");
            userToSave.setProfiles(List.of(profileRecruiter));
            responseRecruiterDTO = iRecruiterService.createRecruiter(
                    userToSave,
                    RequestRecruiterDTO.builder()
                            .name(requestUserDTO.getName())
                            .surnames(requestUserDTO.getSurnames())
                            .username(requestUserDTO.getUsername())
                            .img_profile(requestUserDTO.getImg_profile())
                            .build()
            );
        } else {
            Profile profileCandidate = iProfileRepository.findByName("ROLE_CANDIDATO");
            userToSave.setProfiles(List.of(profileCandidate));
        }
        return ResponseUserDTO.builder()
                .idUser(responseRecruiterDTO.getIdRecruiter())
                .email(userToSave.getEmail())
                .name(responseRecruiterDTO.getName())
                .surnames(responseRecruiterDTO.getSurnames())
                .username(responseRecruiterDTO.getUsername())
                .imageProfile(null)
                .status(responseRecruiterDTO.isStatus())
                .build();
    }

    @Override
    public ResponseUserDTO updateUser(Long idUser) {
        return null;
    }

    @Override
    public void deleteUser(Long idUser) {

    }
}
