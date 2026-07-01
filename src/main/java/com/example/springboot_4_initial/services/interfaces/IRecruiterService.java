package com.example.springboot_4_initial.services.interfaces;

import com.example.springboot_4_initial.dto.user.recruiter.RequestRecruiterDTO;
import com.example.springboot_4_initial.dto.user.recruiter.ResponseRecruiterDTO;
import com.example.springboot_4_initial.models.users.User;

public interface IRecruiterService {
    public abstract ResponseRecruiterDTO createRecruiter(User user, RequestRecruiterDTO requestRecruiterDTO);
}
