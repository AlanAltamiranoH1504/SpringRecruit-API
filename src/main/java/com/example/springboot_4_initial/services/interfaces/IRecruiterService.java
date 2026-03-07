package com.example.springboot_4_initial.services.interfaces;

import com.example.springboot_4_initial.dto.recruiter.UpdateRecruiterDTO;
import com.example.springboot_4_initial.models.Recruiter;

import java.util.List;

public interface IRecruiterService {
    public abstract List<Recruiter> list_recruiters(boolean Status);
    public abstract Recruiter save_recruiter(Recruiter recruiter);
    public abstract Recruiter update_recruiter(UpdateRecruiterDTO updateRecruiterDTO, Long id_user);
    public abstract Recruiter get_recruiter(Long id_recruiter);
    public abstract Recruiter show_recruiter(Long id_recruiter_crypt);
    public abstract void delete_recruiter(Long id_recruiter);
    public abstract void destroy_recruiter(Long id_recruiter);
    public abstract boolean username_in_use(String username, Long id_recruiter, boolean is_update);
    public abstract boolean confirm_account(String token_confirm_account, String randome_number);

}
