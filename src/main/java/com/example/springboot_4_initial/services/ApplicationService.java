package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.dto.application.CreateApplicationDTO;
import com.example.springboot_4_initial.dto.application.UpdateApplicationDTO;
import com.example.springboot_4_initial.exceptions.CreatedEntityException;
import com.example.springboot_4_initial.exceptions.ListEmptyException;
import com.example.springboot_4_initial.exceptions.NotFoundEntity;
import com.example.springboot_4_initial.exceptions.application.ApplicationExistsException;
import com.example.springboot_4_initial.exceptions.vancacies.NotFoundEntityException;
import com.example.springboot_4_initial.models.Application;
import com.example.springboot_4_initial.models.Candidate;
import com.example.springboot_4_initial.models.User;
import com.example.springboot_4_initial.models.Vacancy;
import com.example.springboot_4_initial.models.enums.ApplicationStatus;
import com.example.springboot_4_initial.repositories.IApplicationRepository;
import com.example.springboot_4_initial.repositories.ICandidateRepository;
import com.example.springboot_4_initial.repositories.IUserRepository;
import com.example.springboot_4_initial.repositories.IVacancyRepository;
import com.example.springboot_4_initial.services.interfaces.IApplicationService;
import com.example.springboot_4_initial.services.interfaces.ICloudinaryService;
import com.example.springboot_4_initial.services.interfaces.ICryptoService;
import com.example.springboot_4_initial.services.interfaces.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ApplicationService implements IApplicationService {
    @Autowired
    private IApplicationRepository iApplicationRepository;
    @Autowired
    private IVacancyRepository iVacancyRepository;
    @Autowired
    private ICandidateRepository iCandidateRepository;
    @Autowired
    private ICryptoService iCryptoService;
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private ICloudinaryService iCloudinaryService;
    @Autowired
    private IMailService iMailService;

    @Override
    public List<Application> findAllByRecruiter(Long idRecruiter) {
        return List.of();
    }

    @Override
    public List<Application> listByIdCandidate(String idCandidateCrypt) {
        Optional<User> user = iUserRepository.findById(iCryptoService.decrypt(idCandidateCrypt));
        if (!user.isPresent()) {
            throw new NotFoundEntity("El usuario con el id no se encuentra registrado en la db");
        }

        List<Application> applicationList = iApplicationRepository.applicationByIdCandidate(user.get().getCandidate().getId_candidate());
        if (applicationList.isEmpty()) {
            throw new ListEmptyException("El candidato no se ha postulado a ninguna vacante");
        }
        return applicationList;
    }

    @Override
    public List<Application> listByIdRecruiter(Long idRecruiter) {
        return List.of();
    }

    @Override
    public Application findById(Long idApplication) {
        Optional<Application> applicationToFound = iApplicationRepository.findById(idApplication);
        if (!applicationToFound.isPresent()) {
            throw new NotFoundEntityException("No existe una postulación con el id: " + idApplication);
        }
        return applicationToFound.get();
    }

    @Override
    public Application saveApplication(CreateApplicationDTO createApplicationDTO, MultipartFile file) throws IOException {
        this.existsVacancy(createApplicationDTO.getIdVacancy());

        Optional<Application> isExistsApplication = iApplicationRepository.isExistsApplication(createApplicationDTO.getIdCandidate(), createApplicationDTO.getIdVacancy());
        if (isExistsApplication.isPresent()) {
            throw new ApplicationExistsException("El usuario ya se encontraba postulado a la vacante. Puede revisar el estado de la misma");
        }
        Map responseCloudinary = iCloudinaryService.uploadFile(file);

        Application applicationToSave = new Application(
                LocalDateTime.now(),
                LocalDateTime.now(),
                String.valueOf(responseCloudinary.get("secure_url")),
                createApplicationDTO.getComments_candidate(),
                null,
                ApplicationStatus.RECEIVED,
                iVacancyRepository.getReferenceById(createApplicationDTO.getIdVacancy()),
                iCandidateRepository.getReferenceById(createApplicationDTO.getIdCandidate())
        );
        iApplicationRepository.save(applicationToSave);
        if (applicationToSave.getId_application() == null) {
            throw new CreatedEntityException("Ocurrio un error en la aplicación de la vacante");
        }
        return applicationToSave;
    }

    @Override
    public Application updateApplication(UpdateApplicationDTO updateApplicationDTO) {
        Application applicationToUpdate = this.findById(updateApplicationDTO.getIdApplication());

        // * Update data application
        applicationToUpdate.setStatus(ApplicationStatus.valueOf(updateApplicationDTO.getStatus()));
        applicationToUpdate.setNotes_recruiter(updateApplicationDTO.getNotes_recruiter());
        applicationToUpdate.setLast_update(LocalDateTime.now());
        iApplicationRepository.save(applicationToUpdate);

        // * Send mail to candidate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        iMailService.sendMailUpdateApplication(
                applicationToUpdate.getCandidate().getUser().getEmail(),
                "Tu Postulación Ha Sido Actualizada",
                applicationToUpdate.getCandidate().getName_candidate(),
                applicationToUpdate.getVacancy().getName(),
                updateApplicationDTO.getStatus().toLowerCase(),
                LocalDateTime.now().format(formatter)
        );
        return applicationToUpdate;
    }

    @Override
    public void deleteApplication(Long idApplication) {
        Application applicationToDelete = this.findById(idApplication);
        applicationToDelete.setStatus(ApplicationStatus.REJECTED);
        iApplicationRepository.save(applicationToDelete);

        // * Send mail to candidate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        iMailService.sendMailDeleteApplication(
                applicationToDelete.getCandidate().getUser().getEmail(),
                "Tu Postulación Ha Sido Actualizada",
                applicationToDelete.getCandidate().getName_candidate(),
                applicationToDelete.getVacancy().getName(),
                "RECHAZADA",
                LocalDateTime.now().format(formatter)
        );
    }

    @Override
    public void destroyApplication(Long idApplication) {
        iApplicationRepository.deleteById(idApplication);
    }

    @Override
    public void existsVacancy(Long idVacancy) {
        Optional<Vacancy> vacancy = iVacancyRepository.findById(idVacancy);
        if (!vacancy.isPresent()) {
            throw new NotFoundEntityException("La vacante no esta registrada o fue deshabilitada");
        }
    }
}
