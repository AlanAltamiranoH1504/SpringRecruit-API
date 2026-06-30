package com.example.springboot_4_initial.services.interfaces;

import com.example.springboot_4_initial.dto.profile.RequestUpdateProfileDTO;
import com.example.springboot_4_initial.dto.profile.ResponseProfileDTO;
import com.example.springboot_4_initial.dto.profile.RquestProfileDTO;

import java.util.List;

public interface IProfileService {
    public abstract List<ResponseProfileDTO> getAllProfiles(boolean status);
    public abstract ResponseProfileDTO getProfile(Long idProfile);
    public abstract ResponseProfileDTO createProfile(RquestProfileDTO requestProfileDTO);
    public abstract ResponseProfileDTO updateProfile(Long idProfile, RequestUpdateProfileDTO requestUpdateProfileDTO);
    public abstract void deleteProfile(Long idProfile);
    public abstract void destroyProfile(Long idProfile);
    public abstract void changeStatus(Long idProfile);
}
