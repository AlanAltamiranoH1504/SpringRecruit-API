package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.dto.recruiter.UpdateRecruiterDTO;
import com.example.springboot_4_initial.exceptions.CreatedEntityException;
import com.example.springboot_4_initial.exceptions.ListEmptyException;
import com.example.springboot_4_initial.exceptions.NotFoundEntity;
import com.example.springboot_4_initial.exceptions.UpdateException;
import com.example.springboot_4_initial.exceptions.vancacies.NotFoundEntityException;
import com.example.springboot_4_initial.models.Recruiter;
import com.example.springboot_4_initial.models.User;
import com.example.springboot_4_initial.repositories.IRecruiterRepository;
import com.example.springboot_4_initial.repositories.IUserRepository;
import com.example.springboot_4_initial.services.interfaces.IRecruiterService;
import com.example.springboot_4_initial.services.interfaces.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecruiterService implements IRecruiterService {
    @Autowired
    private IRecruiterRepository iRecruiterRepository;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public List<Recruiter> list_recruiters(boolean Status) {
        List<Recruiter> recruiters = iRecruiterRepository.list_recruiters(Status);

        if (recruiters.isEmpty()) {
            throw new ListEmptyException("No existen reclutadores registrados en la base de datos");
        }
        return recruiters;
    }

    @Override
    public Recruiter save_recruiter(Recruiter recruiter) {
        boolean result_username_in_use = this.username_in_use(recruiter.getUsername(), null, false);
        if (result_username_in_use) {
            throw new CreatedEntityException("El username del reclutador ya se encuentra en uso actualmente");
        }
        iRecruiterRepository.save(recruiter);
        return recruiter;
    }

    @Override
    public Recruiter update_recruiter(UpdateRecruiterDTO updateRecruiterDTO, Long id_user) {
        // ! Busqueda de user with email
        Optional<User> user = iUserRepository.get_user_by_email(updateRecruiterDTO.getEmail());
        if (user.isPresent() && user.get().getId_user() != id_user) {
            throw new UpdateException("El email que se pretende guardar ya se encuetra en uso por otro usuario");
        }

        // ! Busqueda del perfil de reclutador y actualizacion
        User user_to_update = iUserService.get_user(id_user);
        Recruiter recruiter = user_to_update.getRecruiter();
        user_to_update.setEmail(updateRecruiterDTO.getEmail());
        BeanUtils.copyProperties(updateRecruiterDTO, recruiter);

        // ! Guardado de datos para tbl_users y tbl_recruiters
        iUserRepository.save(user_to_update);
        iRecruiterRepository.save(recruiter);
        return recruiter;
    }

    @Override
    public Recruiter get_recruiter(Long id_recruiter) {
        Optional<Recruiter> recruiter_to_show = iRecruiterRepository.findById(id_recruiter);
        if (!recruiter_to_show.isEmpty()) {
            return recruiter_to_show.get();
        }
        throw new NotFoundEntityException("El reclutador con id " + id_recruiter + " no existe en la base de datos");
    }

    @Override
    public Recruiter show_recruiter(Long id_recruiter_crypt) {
        User recruiter_to_show = iUserService.get_user(id_recruiter_crypt);
        if (recruiter_to_show == null) {
            throw new NotFoundEntity("El reclutador con id " + id_recruiter_crypt + " no fue encontrado en la db");
        }
        return recruiter_to_show.getRecruiter();
    }

    @Override
    public void delete_recruiter(Long id_recruiter) {
        Recruiter recruiter_to_delete = this.get_recruiter(id_recruiter);
        recruiter_to_delete.setStatus(false);
        iRecruiterRepository.save(recruiter_to_delete);
    }

    @Override
    public void destroy_recruiter(Long id_recruiter) {
        Recruiter recruiter_to_destroy = this.get_recruiter(id_recruiter);
        User super_user = iUserService.get_user(recruiter_to_destroy.getUser().getId_user());
        iUserService.destroy_user(super_user.getId_user());
    }

    @Override
    public boolean username_in_use(String username, Long id_recruiter, boolean is_update) {
        if (!is_update) {
            Recruiter result_in_use = iRecruiterRepository.username_in_use(username);
            if (result_in_use != null) {
                return true;
            } else {
                return false;
            }
        } else {
            Recruiter result_with_id = iRecruiterRepository.username_in_use(username);
            if (result_with_id != null && result_with_id.getId_recruiter() != id_recruiter) {
                return true;
            }
            return false;
        }
    }

    @Override
    public boolean confirm_account(String token_confirm_account, String randome_number) {
        Recruiter recruiter_by_token_confirm_account = iRecruiterRepository.get_recruiter_by_token_account(token_confirm_account);
        if (recruiter_by_token_confirm_account == null) {
            throw new NotFoundEntityException("No se encontro algun usuario con el token " + token_confirm_account);
        }
        if (!recruiter_by_token_confirm_account.getRandome_number().equals(randome_number)) {
            throw new NotFoundEntityException("Cadena de numeros no valida o corrupta para el reclutador");
        }

        recruiter_by_token_confirm_account.setRandome_number(null);
        recruiter_by_token_confirm_account.setToken_confirm_account(null);
        iRecruiterRepository.save(recruiter_by_token_confirm_account);
        return true;
    }
}
