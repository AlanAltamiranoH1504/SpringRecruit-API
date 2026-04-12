package com.example.springboot_4_initial.controllers;

import com.example.springboot_4_initial.dto.auth.*;
import com.example.springboot_4_initial.repositories.IUserRepository;
import com.example.springboot_4_initial.security.JwtService;
import com.example.springboot_4_initial.services.interfaces.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IAuthService iAuthService;
    @Autowired
    private ICandidateService iCandidateService;
    @Autowired
    private IRecruiterService iRecruiterService;
    @Autowired
    private IAdminService iAdminService;
    @Autowired
    private IResponseService iResponseService;
    @Autowired
    private ICryptoService iCryptoService;
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/save_recruiter")
    public ResponseEntity<?> save_recruiter(@Valid @RequestBody CreateRecluiterDTO createRecluiterDTO) {
        Map<String, Object> res = new HashMap<>();
        iAuthService.save_recruiter(createRecluiterDTO);

        res.put("status", true);
        res.put("message", "Cuenta reclutador creada correctamente");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/save_candidate")
    private ResponseEntity<?> save_candidate(@Valid @RequestBody CreateCandidateDTO createCandidateDTO) {
        Map<String, Object> res = new HashMap<>();
        iAuthService.save_candidate(createCandidateDTO);

        res.put("status", true);
        res.put("message", "Cuenta candidato creada correctamente");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/save_admin")
    private ResponseEntity<?> save_admin(@Valid @RequestBody CreateSuperAdminDTO createSuperAdminDTO) {
        Map<String, Object> res = new HashMap<>();
        iAdminService.save_admin(createSuperAdminDTO);
        res.put("status", true);
        res.put("message", "Cuenta adminitradora creada correctamente");

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login_user(@Valid @RequestBody LoginDTO loginDTO) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("token", iAuthService.login_user(loginDTO.getEmail(), loginDTO.getPassword()).getToken());
        response.put("isRecruiter", iAuthService.login_user(loginDTO.getEmail(), loginDTO.getPassword()).isAdmin());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/confirm_account_recluiter")
    public ResponseEntity<?> confirm_account(@Valid @RequestBody ConfirmAccountDTO confirmAccountDTO) {
        Map<String, Object> json = new HashMap<>();

        iRecruiterService.confirm_account(confirmAccountDTO.getToken_confirm_account(), confirmAccountDTO.getRandome_number());
        json.put("status", true);
        json.put("message", "Usuario confirmado correctamente");

        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @PostMapping("/confirm_candidate")
    public ResponseEntity<?> confirm_candidate(@Valid @RequestBody ConfirmCandidateDTO confirmCandidateDTO) {
        Map<String, Object> json = new HashMap<>();

        iCandidateService.confirm_account(confirmCandidateDTO.getToken_confirm_account(), confirmCandidateDTO.getRandome_number());
        json.put("status", true);
        json.put("message", "Usuario confirmado correctamente");
        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @GetMapping("/decrypt/{id_crypt}")
    public ResponseEntity<?> decrypt_id_user(@PathVariable("id_crypt") String id_crytp) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(iResponseService.generate_response(true, String.valueOf(iCryptoService.decrypt(id_crytp))));
    }

    @PostMapping("/forget_password")
    public ResponseEntity<?> forgetPassword(@Valid @RequestBody ForgetPasswordDTO forgetPasswordDTO) {
        iAuthService.forgetPassword(forgetPasswordDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                iResponseService.generate_response(true, "Email de recuperación de contraseña enviado")
        );
    }

    @PostMapping("/save_new_password")
    public ResponseEntity<?> saveNewPassword(@Valid @RequestBody SaveNewPasswordDTO saveNewPasswordDTO) {
        iAuthService.saveNewPassword(saveNewPasswordDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                iResponseService.generate_response(true, "Contraseña actualizada correctamente")
        );
    }

    @PostMapping("/token_valid")
    public ResponseEntity<?> isTokenJWTValid(@Valid @RequestBody IsJwtValidDTO isJwtValidDTO) {
        iAuthService.isJwtValid(isJwtValidDTO.getToken());
        return ResponseEntity.status(HttpStatus.OK).body(
                iResponseService.generate_response(true, "Token jwt valido")
        );
    }

    @PostMapping("/is_recruiter")
    public ResponseEntity<?> isRecruiter(@Valid @RequestBody IsJwtValidDTO isJwtValidDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(
                iResponseService.generate_response(iAuthService.isRecruiter(
                        iUserRepository.getReferenceById(
                                jwtService.extract_id_user(isJwtValidDTO.getToken())
                        )
                ), "Resultado")
        );
    }
}
