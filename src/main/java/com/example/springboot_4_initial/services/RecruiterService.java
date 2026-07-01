package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.dto.user.recruiter.RequestRecruiterDTO;
import com.example.springboot_4_initial.dto.user.recruiter.ResponseRecruiterDTO;
import com.example.springboot_4_initial.models.users.Recruiter;
import com.example.springboot_4_initial.models.users.User;
import com.example.springboot_4_initial.repositories.IRecruiterRepository;
import com.example.springboot_4_initial.services.interfaces.IRecruiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecruiterService implements IRecruiterService {
    private final IRecruiterRepository iRecruiterRepository;


    @Override
    public ResponseRecruiterDTO createRecruiter(User user, RequestRecruiterDTO requestRecruiterDTO) {
        Recruiter recruiterToSave = Recruiter.builder()
                .name(requestRecruiterDTO.getName())
                .surnames(requestRecruiterDTO.getSurnames())
                .username(requestRecruiterDTO.getUsername())
                .img_profile(null)
                .token_confirm_account(UUID.randomUUID().toString())
                .token_reset_password(null)
                .randome_number(Integer.toString(1504))
                .status(true)
                .user(user)
                .build();
        iRecruiterRepository.save(recruiterToSave);
        return ResponseRecruiterDTO.builder()
                .idRecruiter(recruiterToSave.getId_recruiter())
                .name(recruiterToSave.getName())
                .surnames(recruiterToSave.getSurnames())
                .username(recruiterToSave.getUsername())
                .imgProfile(recruiterToSave.getImg_profile())
                .tokenConfirmAccount(recruiterToSave.getToken_confirm_account())
                .tokenResetPasword(recruiterToSave.getToken_reset_password())
                .randomeNumber(recruiterToSave.getRandome_number())
                .status(true)
                .build();
    }
}
