package com.example.springboot_4_initial.services.interfaces;

import com.example.springboot_4_initial.dto.application.ApplicationByIdRecruiter;
import com.example.springboot_4_initial.dto.application.CreateApplicationDTO;
import com.example.springboot_4_initial.dto.application.UpdateApplicationDTO;
import com.example.springboot_4_initial.models.Application;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IApplicationService {
    public abstract List<Application> findAllByRecruiter(Long idRecruiter);
    public abstract List<Application> listByIdCandidate(String idCandidateCrypt);
    public abstract List<Application> listByIdRecruiter(Long idRecruiter);
    public abstract List<ApplicationByIdRecruiter> applicationsByIdRecruiter(String tokenJWT);
    public abstract Application findById(Long idApplication);
    public abstract Application saveApplication(CreateApplicationDTO createApplicationDTO, MultipartFile file) throws IOException;
    public abstract Application updateApplication(UpdateApplicationDTO updateApplicationDTO);
    public abstract void deleteApplication(Long idApplication);
    public abstract void destroyApplication(Long idApplication);
    public abstract void existsVacancy(Long idVacancy);
}
