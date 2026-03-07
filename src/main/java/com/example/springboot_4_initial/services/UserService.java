package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.dto.user.UpdateUserDTO;
import com.example.springboot_4_initial.exceptions.CreatedEntityException;
import com.example.springboot_4_initial.exceptions.ListEmptyException;
import com.example.springboot_4_initial.exceptions.users.RemoveProfileException;
import com.example.springboot_4_initial.exceptions.users.UpdateProfileException;
import com.example.springboot_4_initial.exceptions.vancacies.NotFoundEntityException;
import com.example.springboot_4_initial.models.Profile;
import com.example.springboot_4_initial.models.User;
import com.example.springboot_4_initial.repositories.IProfileRepository;
import com.example.springboot_4_initial.repositories.IUserRepository;
import com.example.springboot_4_initial.services.interfaces.IMailService;
import com.example.springboot_4_initial.services.interfaces.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    IProfileRepository iProfileRepository;
    @Autowired
    private IMailService iMailService;

    @Override
    public List<User> list_users(boolean status) {
        List<User> list_users = iUserRepository.list_users_by_status(status);
        if (list_users.isEmpty()) {
            throw new ListEmptyException("El retorno de la lista de usuario se encuentra vacia dentro de la db");
        }
        return list_users;
    }

    @Override
    public User get_user(Long id) {
        Optional<User> user_to_show = iUserRepository.findById(id);
        if (user_to_show.isEmpty()) {
            throw new NotFoundEntityException("No se encontro registro de usuario con id " + id + " dentro de la db");
        }
        return user_to_show.get();
    }

    @Override
    public User save_user(User user) {
        String uuid = UUID.randomUUID().toString();
        int randome_number = (int) (Math.random() * 90000) + 10000;

        User user_to_save = iUserRepository.save(user);
//        if (user_to_save.getId() == null) {
//            throw new CreatedEntityException("Ocurrio un error en la creacion del usuario dentro de la db");
//        }
//        iMailService.send_mail_confirm_account_reclutador(user.getEmail(), "Confirma tu Cuenta", user.getName(), uuid, randome_number);
//        user_to_save.setToken_confirm_account(uuid);
//        user_to_save.setRandome_number(String.valueOf(randome_number));
//        iUserRepository.save(user_to_save);
        return user_to_save;
    }

    @Override
    public User update_user(Long id, List<Long> ids_profiles, UpdateUserDTO updateUserDTO) {
        User user_to_update = this.get_user(id);
        boolean result_add_profiles = this.add_profile(id, ids_profiles);
        if (!result_add_profiles) {
            throw new UpdateProfileException("Ocurrio un error en la actualizacion de perfiles de usuario");
        }
        BeanUtils.copyProperties(updateUserDTO, user_to_update);
        iUserRepository.save(user_to_update);
        return user_to_update;
    }

    @Override
    public boolean delete_user(Long id) {
        User user_to_delete = this.get_user(id);
        user_to_delete.setStatus(false);
        iUserRepository.save(user_to_delete);
        return true;
    }

    @Override
    public void destroy_user(Long id_user) {
        User user_to_destroy = this.get_user(id_user);
        iUserRepository.delete(user_to_destroy);
    }

    @Override
    public boolean add_profile(Long id_user, List<Long> id_profiles) {
        List<Profile> profiles_to_add = iProfileRepository.findAllById(id_profiles);
        User user_to_update = this.get_user(id_user);
        if (profiles_to_add.isEmpty()) {
            throw new ListEmptyException("La lista de perfiles a agregar se encuentra vacia. Ningun perfil es valido para agregar");
        }
        List<Profile> currentProfiles = user_to_update.getProfiles();
        for (var p : profiles_to_add) {
            if (!currentProfiles.contains(p)) {
                currentProfiles.add(p);
            }
        }
        iUserRepository.save(user_to_update);
        return true;
    }

    @Override
    public boolean remove_profiles(Long id_user, List<Long> ids_profile) {
        List<Profile> profiles_to_remove = iProfileRepository.findAllById(ids_profile);
        User user_to_update = this.get_user(id_user);

        if (profiles_to_remove.isEmpty()) {
            throw new ListEmptyException("La lista de perfiles a eiminar se encuentra vacia. Ningun perfil es valido para eliminar");
        }

        if (profiles_to_remove.size() >= user_to_update.getProfiles().size()) {
            throw new RemoveProfileException("El numero de perfiles a retirar es mayor o igual a los que tiene el usuario");
        }

        List<Profile> current_profiles = user_to_update.getProfiles();
        for (var p : profiles_to_remove) {
            if (profiles_to_remove.contains(p)) {
                current_profiles.remove(p);
            }
        }
        iUserRepository.save(user_to_update);
        return true;
    }

    @Override
    public boolean update_img_profile(Long id_user, String url_img) {
//        Optional<User> user_to_update = iUserRepository.findById(id_user);
//        user_to_update.get().setImg_profile(url_img);
//        iUserRepository.save(user_to_update.get());
        return true;
    }

    @Override
    public Optional<User> get_user_by_email(String email) {
        Optional<User> user_to_found = iUserRepository.get_user_by_email(email);
        if (user_to_found.isPresent()) {
            return user_to_found;
        }
        throw new NotFoundEntityException("El usuario con el email " + email + " no se encuentra registrado");
    }

    @Override
    public boolean confirm_account(String token_confirm_account, String randome_number) {
//        Optional<User> user_by_token_confirm_account = iUserRepository.get_user_by_token_confirm_account(token_confirm_account);
//        if (user_by_token_confirm_account.isEmpty()) {
//            throw new NotFoundEntityException("No se encontro algun usuario con el token " + token_confirm_account);
//        }
//        if (!user_by_token_confirm_account.get().getRandome_number().equals(randome_number)) {
//            throw new NotFoundEntityException("Cadena de numeros no valida para el usuario o corrupta");
//        }
//        User user = user_by_token_confirm_account.get();
//        user.setRandome_number(null);
//        user.setToken_confirm_account(null);
//        iUserRepository.save(user);
        return true;
    }
}
