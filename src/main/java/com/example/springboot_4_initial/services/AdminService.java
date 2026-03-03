package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.dto.admin.UpdateAdminDTO;
import com.example.springboot_4_initial.dto.auth.CreateSuperAdminDTO;
import com.example.springboot_4_initial.exceptions.CreatedEntityException;
import com.example.springboot_4_initial.exceptions.ListEmptyException;
import com.example.springboot_4_initial.exceptions.NotFoundEntity;
import com.example.springboot_4_initial.exceptions.UpdateException;
import com.example.springboot_4_initial.exceptions.vancacies.NotFoundEntityException;
import com.example.springboot_4_initial.models.*;
import com.example.springboot_4_initial.repositories.*;
import com.example.springboot_4_initial.services.interfaces.IAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IAdminService {
    @Autowired
    private IAdminRepository iAdminRepository;
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private IProfileRepository iProfileRepository;
    @Autowired
    private IRecruiterRepository iRecruiterRepository;
    @Autowired
    private ICandidateRepository iCandidateRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<Admin> list_admin(boolean status) {
        List<Admin> admins = iAdminRepository.admins_by_status(status);
        if (admins.isEmpty()) {
            throw new ListEmptyException("La lista de administradores se encuentra vacia");
        }
        return admins;
    }

    @Override
    public List<Recruiter> list_recruiters(boolean status) {
        List<Recruiter> recruiters = iRecruiterRepository.list_recruiters(status);
        if (recruiters.isEmpty()) {
            throw new ListEmptyException("La lista de reclutadores se encuentra vacia");
        }
        return recruiters;
    }

    @Override
    public List<Candidate> list_candidates(boolean status) {
        List<Candidate> candidates = iCandidateRepository.candidates_by_status(status);
        if (candidates.isEmpty()) {
            throw new ListEmptyException("La lista de candidatos se encuentra vacia");
        }
        return candidates;
    }

    @Override
    public Admin get_admin(Long id_admin) {
        Optional<Admin> admin_to_show = iAdminRepository.findById(id_admin);
        if (admin_to_show.isEmpty()) {
            throw new NotFoundEntityException("El administrador con id " + id_admin + " no existe en la base de datos");
        }
        return admin_to_show.get();
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

    @Transactional
    @Override
    public Admin update_admin(UpdateAdminDTO updateAdminDTO, Long id_admin) {
        Optional<User> user_by_mail = iUserRepository.get_user_by_email(updateAdminDTO.getEmail());
        if (user_by_mail.isPresent() && user_by_mail.get().getId_user() != id_admin) {
            throw new UpdateException("El email que se quiere guardar ya se encuentra en uso por otro administrador");
        }
        Optional<User> user_to_update = iUserRepository.findById(id_admin);
        if (user_to_update.isEmpty()) {
            throw new NotFoundEntityException("El administrador con id  " + id_admin + " no existe en la base de datos");
        }
        // * Seteo de nuevo email
        user_to_update.get().setEmail(updateAdminDTO.getEmail());
        iUserRepository.save(user_to_update.get());

        // * Seteo de informacion de admin
        Admin admin_to_update = user_to_update.get().getAdmin();
        BeanUtils.copyProperties(updateAdminDTO, admin_to_update);
        iAdminRepository.save(admin_to_update);
        return admin_to_update;
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
