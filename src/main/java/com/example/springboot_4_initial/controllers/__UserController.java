package com.example.springboot_4_initial.controllers;

import com.example.springboot_4_initial.dto.user.AddProfilesDTO;
import com.example.springboot_4_initial.dto.user.CreateUserDTO;
import com.example.springboot_4_initial.dto.ListEntityDTO;
import com.example.springboot_4_initial.dto.user.RemoveProfileDTO;
import com.example.springboot_4_initial.dto.user.UpdateUserDTO;
import com.example.springboot_4_initial.models.Profile;
import com.example.springboot_4_initial.security.UserInfoDetails;
import com.example.springboot_4_initial.services.interfaces.ICryptoService;
import com.example.springboot_4_initial.services.interfaces.IImageService;
import com.example.springboot_4_initial.services.interfaces.IProfileService;
import com.example.springboot_4_initial.services.interfaces.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/users")
public class __UserController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IProfileService iProfileService;
    @Autowired
    private IImageService iImageService;
    @Autowired
    ICryptoService iCryptoService;

    @PostMapping("/save")
    public ResponseEntity<?> save_user(@Valid @RequestBody CreateUserDTO createUserDTO) {
        Map<String, Object> json = new HashMap<>();
        List<Profile> profiles = iProfileService.get_profiles_by_id(createUserDTO.getProfiles());
//        iUserService.save_user(new User(createUserDTO.getName(), createUserDTO.getSurnames(), createUserDTO.getEmail(), createUserDTO.getUsername(), passwordEncoder.encode(createUserDTO.getPassword()), createUserDTO.getImg_profile(), true, new Date(), profiles));

        json.put("status", true);
        json.put("message", "Usuario creado correctamente");
        return ResponseEntity.status(HttpStatus.CREATED).body(json);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list_users(@Valid @RequestBody ListEntityDTO listEntityDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(iUserService.list_users(listEntityDTO.getStatus()));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> find_user(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(iUserService.get_user(id));
    }

    @PutMapping("/add_profile/{id}")
    public ResponseEntity<?> add_profile_to_user(@Valid @RequestBody AddProfilesDTO addProfilesDTO, @PathVariable Long id) {
        Map<String, Object> json = new HashMap<>();

        iUserService.add_profile(id, addProfilesDTO.getProfiles());
        json.put("status", true);
        json.put("message", "Perfiles agregados a usuario");
        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @DeleteMapping("/remove_profiles/{id}")
    public ResponseEntity<?> remove_profiles_to_user(@Valid @RequestBody RemoveProfileDTO removeProfileDTO, @PathVariable Long id) {
        Map<String, Object> json = new HashMap<>();
        iUserService.remove_profiles(id, removeProfileDTO.getProfiles());

        json.put("status", true);
        json.put("message", "Perfiles eliminados al usuario");
        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @PutMapping("/update_user/{id}")
    public ResponseEntity<?> update_user(@Valid @RequestBody UpdateUserDTO updateUserDTO, @PathVariable Long id) {
        Map<String, Object> json = new HashMap<>();

        iUserService.update_user(id, updateUserDTO.getProfiles(), updateUserDTO);
        json.put("status", true);
        json.put("message", "Usuario actualizado correctamente");
        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @PutMapping("/update/img_profile/{id}")
    public ResponseEntity<?> update_img_profile(@PathVariable Long id, @RequestParam("img_profile") MultipartFile multipartFile) throws IOException {
        Map<String, Object> json = new HashMap<>();
        String path_img = "C:/Imagenes_Proyectos/SpringBoot/Img_Profiles";
        this.find_user(id);

        String result_path_img = iImageService.save_image(path_img, multipartFile);
        iUserService.update_img_profile(id, result_path_img);
        json.put("status", true);
        json.put("message", "Imagen de perfil actualizada");

        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @DeleteMapping("/delete_user/{id}")
    public ResponseEntity<?> delete_user(@PathVariable Long id) {
        Map<String, Object> json = new HashMap<>();

        iUserService.delete_user(id);
        json.put("status", true);
        json.put("message", "Usuario eliminado correctamente");
        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @GetMapping("/user_in_session")
    public ResponseEntity<?> user_in_session(@AuthenticationPrincipal UserInfoDetails user) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("id_user_encrypt", iCryptoService.encrypt(String.valueOf(user.get_IdUser())));
//        response.put("id_user_decrypt", iCryptoService.decrypt(String.valueOf(id_crypt)));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
