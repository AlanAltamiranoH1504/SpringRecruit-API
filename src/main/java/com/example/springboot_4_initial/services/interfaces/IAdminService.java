package com.example.springboot_4_initial.services.interfaces;

import com.example.springboot_4_initial.dto.admin.UpdateAdminDTO;
import com.example.springboot_4_initial.dto.auth.CreateSuperAdminDTO;
import com.example.springboot_4_initial.models.Admin;
import com.example.springboot_4_initial.models.Candidate;
import com.example.springboot_4_initial.models.Recruiter;

import java.util.List;

public interface IAdminService {
    public abstract List<Admin> list_admin(boolean status);
    public abstract List<Recruiter> list_recruiters(boolean status);
    public abstract List<Candidate> list_candidates(boolean status);
    public abstract Admin get_admin(Long id_admin);
    public abstract Admin save_admin(CreateSuperAdminDTO createSuperAdminDTO);
    public abstract Admin update_admin(UpdateAdminDTO updateAdminDTO, Long id_admin);
    public abstract boolean delete_admin(Long id_admin);
    public abstract boolean destroy_admin(Long id_admin);
}
