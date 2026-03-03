package com.example.springboot_4_initial.services.interfaces;

import com.example.springboot_4_initial.dto.auth.CreateSuperAdminDTO;
import com.example.springboot_4_initial.models.Admin;

import java.util.List;

public interface IAdminService {
    public abstract List<Admin> list_admin(boolean status);
    public abstract Admin get_admin(Long id_admin);
    public abstract Admin save_admin(CreateSuperAdminDTO createSuperAdminDTO);
    public abstract boolean delete_admin(Long id_admin);
    public abstract boolean destroy_admin(Long id_admin);
}
