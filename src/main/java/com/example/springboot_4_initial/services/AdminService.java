package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.dto.auth.CreateSuperAdminDTO;
import com.example.springboot_4_initial.exceptions.CreatedEntityException;
import com.example.springboot_4_initial.models.Admin;
import com.example.springboot_4_initial.models.Profile;
import com.example.springboot_4_initial.models.User;
import com.example.springboot_4_initial.repositories.IAdminRepository;
import com.example.springboot_4_initial.repositories.IProfileRepository;
import com.example.springboot_4_initial.repositories.IUserRepository;
import com.example.springboot_4_initial.services.interfaces.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminService implements IAdminService {
    @Autowired
    private IAdminRepository iAdminRepository;
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private IProfileRepository iProfileRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<Admin> list_admin(boolean status) {
        return List.of();
    }

    @Override
    public Admin get_admin(Long id_admin) {
        return null;
    }

    @Transactional
    @Override
    public Admin save_admin(CreateSuperAdminDTO createSuperAdminDTO) {
        Profile profile = iProfileRepository.get_profile_by_name("ROLE_ADMINISTRADOR");
        if (profile == null) {
            throw new CreatedEntityException("Ocurrio un error en la creación del usuario por la asignación de roles");
        }
        User user_to_save = new User(
                createSuperAdminDTO.getEmail(),
                passwordEncoder.encode(createSuperAdminDTO.getPassword()),
                true,
                Arrays.asList(profile)
        );
        iUserRepository.save(user_to_save);
        Admin admin_to_save = new Admin(
                createSuperAdminDTO.getName_admin(),
                createSuperAdminDTO.getLastname_admin(),
                null,
                createSuperAdminDTO.getCellphone(),
                createSuperAdminDTO.getAddress(),
                null,
                1,
                true,
                user_to_save
        );
        iAdminRepository.save(admin_to_save);
        if (admin_to_save.getId_admin() == null) {
            throw new CreatedEntityException("Ocurrio un error en la creacion del usuario administrador");
        }
        return admin_to_save;
    }

    @Override
    public boolean delete_admin(Long id_admin) {
        return false;
    }

    @Override
    public boolean destroy_admin(Long id_admin) {
        return false;
    }
}
