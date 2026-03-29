package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.dto.auth.CreateCandidateDTO;
import com.example.springboot_4_initial.dto.auth.CreateRecluiterDTO;
import com.example.springboot_4_initial.dto.auth.ForgetPasswordDTO;
import com.example.springboot_4_initial.dto.auth.SaveNewPasswordDTO;
import com.example.springboot_4_initial.exceptions.CreatedEntityException;
import com.example.springboot_4_initial.exceptions.auth.NotCofirmAccountException;
import com.example.springboot_4_initial.exceptions.auth.PasswordIncorrectException;
import com.example.springboot_4_initial.exceptions.vancacies.NotFoundEntityException;
import com.example.springboot_4_initial.models.Candidate;
import com.example.springboot_4_initial.models.Profile;
import com.example.springboot_4_initial.models.Recruiter;
import com.example.springboot_4_initial.models.User;
import com.example.springboot_4_initial.repositories.ICandidateRepository;
import com.example.springboot_4_initial.repositories.IRecruiterRepository;
import com.example.springboot_4_initial.repositories.IUserRepository;
import com.example.springboot_4_initial.security.JwtService;
import com.example.springboot_4_initial.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailService mailService;
    @Autowired
    private IProfileService iProfileService;
    @Autowired
    private IRecruiterService iRecruiterService;
    @Autowired
    private ICandidateService iCandidateService;
    @Autowired
    private ICandidateRepository iCandidateRepository;
    @Autowired
    private IRecruiterRepository iRecruiterRepository;
    @Autowired
    private IUserRepository iUserRepository;


    @Transactional
    @Override
    public Recruiter save_recruiter(CreateRecluiterDTO createRecluiterDTO) {
        // * Save tbl_user
        List<Profile> profiles_recluiter = this.get_profiles(createRecluiterDTO.getRoles());
        String uuid = UUID.randomUUID().toString();
        int randome_number = (int) (Math.random() * 10000);
        User user = new User(
                createRecluiterDTO.getEmail(),
                passwordEncoder.encode(createRecluiterDTO.getPassword()),
                true,
                profiles_recluiter);
        iUserService.save_user(user);
        if (user.getId_user() == null) {
            throw new CreatedEntityException("Ocurrio un error en la creacion del perfil de reclutador");
        }
        Recruiter recruiter = new Recruiter(
                createRecluiterDTO.getName(),
                createRecluiterDTO.getSurnames(),
                createRecluiterDTO.getUsername(),
                null,
                uuid,
                null,
                String.valueOf(randome_number),
                true,
                user
        );
        iRecruiterService.save_recruiter(recruiter);
        mailService.send_mail_confirm_account_reclutador(user.getEmail(), "Confirma tu cuenta", recruiter.getName(), uuid, randome_number);
        return recruiter;
    }

    @Transactional
    @Override
    public Candidate save_candidate(CreateCandidateDTO createCandidateDTO) {
        // * Save tbl_user
        List<Profile> profiles_candidate = this.get_profiles(createCandidateDTO.getRoles());
        String uuid = UUID.randomUUID().toString();
        int randome_number = (int) (Math.random() * 10000);
        User user = new User(createCandidateDTO.getEmail(), passwordEncoder.encode(createCandidateDTO.getPassword()), true, profiles_candidate);
        iUserService.save_user(user);
        if (user.getId_user() == null) {
            throw new CreatedEntityException("Ocurrio un error en la creacion del perfil de candidato");
        }
        Candidate candidate = new Candidate(
                createCandidateDTO.getName_candidate(),
                createCandidateDTO.getLastname_candidate(),
                null,
                null,
                createCandidateDTO.getCellphone(),
                createCandidateDTO.getAddress(),
                uuid,
                null,
                String.valueOf(randome_number),
                true,
                user
        );
        iCandidateService.save_candidate(candidate);
        mailService.send_mail_confirm_account_candidate(user.getEmail(), "Confirma tu cuenta", candidate.getName_candidate(), uuid, randome_number);
        return candidate;
    }

    @Override
    public List<Profile> get_profiles(List<Long> id_profiles) {
        List<Profile> valid_profiles = new ArrayList<>();
        for (var p : id_profiles) {
            Profile profile_to_get = iProfileService.get_profile(p);
            if (profile_to_get.getProfile().equals("ROLE_RECLUTADOR") || profile_to_get.getProfile().equals("ROLE_CANDIDATO")) {
                valid_profiles.add(profile_to_get);
            }
        }
        return valid_profiles;
    }

    @Override
    public void forgetPassword(ForgetPasswordDTO forgetPasswordDTO) {
        Optional<User> userToFound = iUserService.get_user_by_email(forgetPasswordDTO.getEmail());
        if (!userToFound.isPresent()) {
            throw new NotFoundEntityException("Usuario no encontrado con el email " + forgetPasswordDTO.getEmail());

        }
        int randome_number = (int) (Math.random() * 10000);
        String token = UUID.randomUUID().toString();

        if (userToFound.get().getCandidate() != null) { // * FLUJO CANDIDATO
            User user = userToFound.get();
            user.getCandidate().setRandome_number(String.valueOf(randome_number));
            user.getCandidate().setToken_reset_password(token);
            mailService.sendMailResetPasswordCandidate(
                    userToFound.get().getEmail(),
                    "RECUPERACIÓN DE CONTRASEÑA",
                    token,
                    String.valueOf(randome_number)
            );
            iUserService.save_user(user);
        } else { // * FLUJO RECLUTADO
            User user = userToFound.get();
            user.getRecruiter().setToken_reset_password(token);
            user.getRecruiter().setRandome_number(String.valueOf(randome_number));
            mailService.sendMailResetPasswordRecruiter(
                    user.getEmail(),
                    "RECUPERACIÓN DE CONTRASEÑA",
                    token,
                    String.valueOf(randome_number)
            );
            iUserService.save_user(user);
        }
    }

    @Override
    public void saveNewPassword(SaveNewPasswordDTO saveNewPasswordDTO) {
        Optional<Candidate> isCandidate = iCandidateRepository.candidateToResetPassword(saveNewPasswordDTO.getToken(), saveNewPasswordDTO.getRandomNumber());
        Optional<Recruiter> isRecruiter = iRecruiterRepository.recruiterToResetPassword(saveNewPasswordDTO.getToken(), saveNewPasswordDTO.getRandomNumber());

        if (isCandidate.isPresent()) { // * Save to Candidate
            Candidate candidate = isCandidate.get();
            candidate.setToken_reset_password(null);
            candidate.setRandome_number(null);
            candidate.getUser().setPassword(passwordEncoder.encode(saveNewPasswordDTO.getNewPassword()));
            iUserService.save_user(candidate.getUser());

        } else if (isRecruiter.isPresent()) { // * Save to Recruiter
            Recruiter recruiter = isRecruiter.get();
            recruiter.setToken_reset_password(null);
            recruiter.setRandome_number(null);
            recruiter.getUser().setPassword(passwordEncoder.encode(saveNewPasswordDTO.getNewPassword()));
            iUserService.save_user(recruiter.getUser());
        }
    }

    @Override
    public String login_user(String email, String password) {
        // * Search tbl_user
        Optional<User> user_by_email = iUserService.get_user_by_email(email);
        if (user_by_email.isEmpty()) {
            throw new NotFoundEntityException("El usuario a buscar no esta registrado");
        }

        if (user_by_email.get().getCandidate() != null) {
            // * Candidate
            Candidate candidate = user_by_email.get().getCandidate();
            if (candidate.getToken_confirm_account() != null) {
                mailService.send_mail_confirm_account_candidate(
                        user_by_email.get().getEmail(),
                        "Confirma tu Cuenta",
                        candidate.getName_candidate(),
                        candidate.getToken_confirm_account(),
                        Integer.parseInt(candidate.getRandome_number())
                );
                throw new NotCofirmAccountException("El usuario candidato no ha confirmado su cuenta de manera correcta. Verifica tu correo para confirmación");
            }
        } else if (user_by_email.get().getRecruiter() != null) {
            // * Recluiter
            Recruiter recruiter = user_by_email.get().getRecruiter();
            if (recruiter.getToken_confirm_account() != null) {
                mailService.send_mail_confirm_account_reclutador(
                        user_by_email.get().getEmail(),
                        "Confirma tu cuenta",
                        recruiter.getName(),
                        recruiter.getToken_confirm_account(),
                        Integer.parseInt(recruiter.getRandome_number())
                );
                throw new NotCofirmAccountException("El usuario reckutador no ha confirmado su cuenta de manera correcta. Verifica tu correo para confirmación");
            }
        }
        if (passwordEncoder.matches(password, user_by_email.get().getPassword())) {
            return jwtService.generateTokenJWT(user_by_email.get());
        }
        throw new PasswordIncorrectException("El password es incorrecto");
    }
}
