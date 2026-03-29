package com.example.springboot_4_initial.services.interfaces;

import com.example.springboot_4_initial.dto.auth.CreateCandidateDTO;
import com.example.springboot_4_initial.dto.auth.CreateRecluiterDTO;
import com.example.springboot_4_initial.dto.auth.ForgetPasswordDTO;
import com.example.springboot_4_initial.dto.auth.SaveNewPasswordDTO;
import com.example.springboot_4_initial.models.Candidate;
import com.example.springboot_4_initial.models.Profile;
import com.example.springboot_4_initial.models.Recruiter;

import java.util.List;

public interface IAuthService {
    public abstract Recruiter save_recruiter(CreateRecluiterDTO createRecluiterDTO);
    public abstract Candidate save_candidate(CreateCandidateDTO createCandidateDTO);
    public abstract List<Profile> get_profiles(List<Long> id_profiles);
    public abstract void forgetPassword(ForgetPasswordDTO forgetPasswordDTO);
    public abstract void saveNewPassword(SaveNewPasswordDTO saveNewPasswordDTO);
    public abstract String login_user(String email, String password);
}
